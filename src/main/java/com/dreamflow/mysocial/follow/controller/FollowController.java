package com.dreamflow.mysocial.follow.controller;

import com.dreamflow.mysocial.follow.dto.FollowDto;
import com.dreamflow.mysocial.follow.entity.Follow;
import com.dreamflow.mysocial.follow.mapper.FollowMapper;
import com.dreamflow.mysocial.follow.service.FollowService;
import com.dreamflow.mysocial.global.common.BaseResponse;
import com.dreamflow.mysocial.global.common.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;
    @PostMapping("/follow/{memberId}")
    public BaseResponse<String> follow(@PathVariable Long memberId, @CurrentUser Long loginId) {
        Follow follow = followService.createFollow(memberId, loginId);
        String result = follow.getToMember().getName() + "님을 팔로우 하였습니다.";
        return new BaseResponse<>(result);
    }

    @GetMapping("/follow/{memberId}")
    public BaseResponse<List<FollowDto.followingResponse>> getFollowList(@PathVariable Long memberId) {
        List<Follow> followList = followService.getFollowList(memberId);
        List<FollowDto.followingResponse> response = followList.stream()
                .map(FollowMapper::toResponseFollowing)
                .collect(Collectors.toList());
        return new BaseResponse<>(response);
    }

    @GetMapping("/follower/{memberId}")
    public BaseResponse<List<FollowDto.followerResponse>> getFollowerList(@PathVariable Long memberId) {
        List<Follow> followList = followService.getFollowerList(memberId);
        List<FollowDto.followerResponse> response = followList.stream()
                .map(FollowMapper::toResponseFollower)
                .collect(Collectors.toList());
        return new BaseResponse<>(response);
    }

    @DeleteMapping("/follow/{memberId}")
    public BaseResponse<String> deleteFollow(@PathVariable Long memberId, @CurrentUser Long loginId) {
        followService.deleteFollow(memberId, loginId);
        return new BaseResponse<>("팔로우 취소 성공");
    }
}
