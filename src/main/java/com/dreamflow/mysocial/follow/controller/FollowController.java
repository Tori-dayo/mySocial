package com.dreamflow.mysocial.follow.controller;

import com.dreamflow.mysocial.follow.dto.FollowDto;
import com.dreamflow.mysocial.follow.entity.Follow;
import com.dreamflow.mysocial.follow.mapper.FollowMapper;
import com.dreamflow.mysocial.follow.service.FollowService;
import com.dreamflow.mysocial.global.common.BaseResponse;
import com.dreamflow.mysocial.global.common.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;

    @PostMapping("/follow/{memberId}")
    public BaseResponse<String> follow(@PathVariable Long memberId, @CurrentUser Long loginId) {
        Follow follow = followService.createFollow(memberId, loginId);
        String result = String.valueOf(follow.getToMember());
        return new BaseResponse<>(result + "팔로우 성공");
    }

    @GetMapping("/follow/{memberId}")
    public BaseResponse<List<FollowDto.followingResponse>> getFollowList(@PathVariable Long memberId) {
        // 팔로잉 목록을 가져옵니다.
        List<Follow> followList = followService.getFollowList(memberId);

        // 팔로잉 목록을 기반으로 팔로잉 응답 리스트를 생성합니다.
        List<FollowDto.followingResponse> response = new ArrayList<>();
        for (Follow follow : followList) {
            // 각 팔로잉에 대한 응답을 생성하여 리스트에 추가합니다.
            FollowDto.followingResponse followingResponse = new FollowDto.followingResponse();
            followingResponse.setId(follow.getId());
            followingResponse.setMemberId(follow.getToMember().getId());
            followingResponse.setName(follow.getToMember().getName());
            response.add(followingResponse);
        }

        // 팔로잉 응답 리스트를 BaseResponse에 담아 반환합니다.
        return new BaseResponse<>(response);
    }

    @GetMapping("/follower/{memberId}")
    public BaseResponse<List<FollowDto.followerResponse>> getFollowerList(@PathVariable Long memberId) {
        // 팔로워 목록을 가져옵니다.
        List<Follow> followList = followService.getFollowerList(memberId);

        // 팔로워 목록을 기반으로 팔로워 응답 리스트를 생성합니다.
        List<FollowDto.followerResponse> response = new ArrayList<>();
        for (Follow follow : followList) {
            // 각 팔로워에 대한 응답을 생성하여 리스트에 추가합니다.
            FollowDto.followerResponse followerResponse = new FollowDto.followerResponse();
            followerResponse.setId(follow.getId());
            followerResponse.setMemberId(follow.getFromMember().getId());
            followerResponse.setName(follow.getFromMember().getName());
            response.add(followerResponse);
        }

        // 팔로워 응답 리스트를 BaseResponse에 담아 반환합니다.
        return new BaseResponse<>(response);
    }

}
