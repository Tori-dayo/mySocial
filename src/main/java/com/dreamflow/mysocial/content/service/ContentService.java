package com.dreamflow.mysocial.content.service;

import com.dreamflow.mysocial.content.entity.Content;
import com.dreamflow.mysocial.content.exception.ContentErrorCode;
import com.dreamflow.mysocial.content.repository.ContentRepository;
import com.dreamflow.mysocial.follow.entity.Follow;
import com.dreamflow.mysocial.follow.repository.FollowRepository;
import com.dreamflow.mysocial.global.exception.BaseException;
import com.dreamflow.mysocial.member.entity.Member;
import com.dreamflow.mysocial.member.exception.MemberErrorCode;
import com.dreamflow.mysocial.member.repository.MemberRepository;
import com.dreamflow.mysocial.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ContentService {
    private final ContentRepository contentRepository;
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final FollowRepository followRepository;

    public Content createContent(Content content, MultipartFile image, Long id) throws IOException {
        content.setMember(memberRepository.findById(id).orElseThrow(()->new BaseException(MemberErrorCode.NOT_FOUND_MEMBER)));
        return contentRepository.save(content);
    }

    public Content patchContent(Long id, Content content) {
        Content findContent = findVerifiedContent(id);
        findContent.setContent(content.getContent());
        findContent.setTitle(content.getTitle());
        findContent.setImageUrl(content.getImageUrl());
        return contentRepository.save(findContent);
    }

    //특정 게시글을 불러오는 메서드
    public Content findContent(Long id) {
        return findVerifiedContent(id);
    }

    //피드에 팔로우 한 사용자들의 글만 불러오는 메서드
    public List<Content> findFeedContents(Long memberId) {
        List<Member> followedMembers = getFollowedMembers(memberId);

        List<Content> feedContents = new ArrayList<>();

        for (Member followedMember : followedMembers) {
            List<Content> followedMemberContents = contentRepository.findByMember(followedMember);
            feedContents.addAll(followedMemberContents);
        }

        return feedContents;
    }

    //특정 사용자의 모든 글 리스트를 불러오는 메서드
    public List<Content> findContents(Long memberId) {
        return new ArrayList<>(contentRepository.findByMemberId(memberId));
    }

    public void deleteContent(Long id) {
        contentRepository.deleteById(id);
    }

    public Content findVerifiedContent(Long id) {
        return contentRepository.findById(id)
                .orElseThrow(() -> BaseException.type(ContentErrorCode.NOT_FOUND_CONTENT));
    }

    //팔로우 한 멤버들의 리스트를 가져오는 메서드
    private List<Member> getFollowedMembers(Long memberId) {
        Member member = memberService.findVerifiedMember(memberId);
        return followRepository.findByFromMember(member).stream()
                .map(Follow::getToMember)
                .collect(Collectors.toList());
    }
}