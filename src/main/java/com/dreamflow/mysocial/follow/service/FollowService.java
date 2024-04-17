package com.dreamflow.mysocial.follow.service;

import com.dreamflow.mysocial.follow.entity.Follow;
import com.dreamflow.mysocial.follow.exception.FollowErrorCode;
import com.dreamflow.mysocial.follow.repository.FollowRepository;
import com.dreamflow.mysocial.global.exception.BaseException;
import com.dreamflow.mysocial.member.entity.Member;
import com.dreamflow.mysocial.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class FollowService {
    private final FollowRepository followRepository;
    private final MemberService memberService;

    public Follow createFollow(Long followId, Long loginId) {
        if (followId.equals(loginId)) {
            throw new BaseException(FollowErrorCode.SELF_FOLLOW);
        } else if (isDuplicateFollow(loginId, followId)) {
            throw new BaseException(FollowErrorCode.ALREADY_FOLLOWING);
        }

        Follow follow = Follow.builder()
                .fromMember(memberService.findVerifiedMember(loginId))
                .toMember(memberService.findVerifiedMember(followId))
                .build();
        return followRepository.save(follow);
    }

    public List<Follow> getFollowList(Long selectUserId) {
        return followRepository.findByFromMember(memberService.findVerifiedMember(selectUserId));
    }

    public List<Follow> getFollowerList(Long selectUserId) {
        return followRepository.findByToMember(memberService.findVerifiedMember(selectUserId));
    }

    public void deleteFollow(Long followId, Long loginId) {
        Optional<Follow> follow = followRepository.findByFromMemberAndToMember(memberService.findVerifiedMember(loginId),
                memberService.findVerifiedMember(followId));
        if(follow.isEmpty()) {
            throw new BaseException(FollowErrorCode.NOT_FOUND_FOLLOW);
        }
        followRepository.delete(follow.get());

    }
    public boolean isDuplicateFollow(Long fromMemberId, Long toMemberId) {
        Member fromMember = memberService.findVerifiedMember(fromMemberId);
        Member toMember = memberService.findVerifiedMember(toMemberId);

        Optional<Follow> existingFollow = followRepository.findByFromMemberAndToMember(fromMember, toMember);
        return existingFollow.isPresent();
    }
}
