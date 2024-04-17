package com.dreamflow.mysocial.follow.mapper;

import com.dreamflow.mysocial.follow.dto.FollowDto;
import com.dreamflow.mysocial.follow.entity.Follow;

public class FollowMapper {
    public static FollowDto.followingResponse toResponseFollowing(Follow follow){
        FollowDto.followingResponse response = new FollowDto.followingResponse();
        response.setId(follow.getId());
        response.setName(follow.getToMember().getName());
        response.setMemberId(follow.getToMember().getId());
        return response;
    }

    public static FollowDto.followerResponse toResponseFollower(Follow follow){
        FollowDto.followerResponse response = new FollowDto.followerResponse();
        response.setId(follow.getId());
        response.setName(follow.getFromMember().getName());
        response.setMemberId(follow.getFromMember().getId());
        return response;
    }
}