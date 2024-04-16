package com.dreamflow.mysocial.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class MemberDto {

    @Getter
    @NoArgsConstructor
    public static class SignUpRequest {
        String email;
        String password;
        String name;
    }
    @Getter
    @NoArgsConstructor
    public static class SignInRequest {
        String email;
        String password;
    }
    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignUpResponse {
        String accessToken;
        String refreshToken;
    }

}
