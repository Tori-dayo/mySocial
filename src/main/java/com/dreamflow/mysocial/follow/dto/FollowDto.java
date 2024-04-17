package com.dreamflow.mysocial.follow.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class FollowDto {
    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class followingResponse{
        private Long id;
        private Long memberId;
        private String name;
    }
    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class followerResponse{
        private Long id;
        private Long memberId;
        private String name;
    }

}
