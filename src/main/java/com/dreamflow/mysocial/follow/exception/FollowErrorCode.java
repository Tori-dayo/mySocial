package com.dreamflow.mysocial.follow.exception;

import com.dreamflow.mysocial.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum FollowErrorCode implements ErrorCode {

    SELF_FOLLOW(HttpStatus.CONFLICT, "FOLLOW_001", "자기 자신을 팔로우할 수 없습니다."),
    ALREADY_FOLLOWING(HttpStatus.CONFLICT, "FOLLOW_002", "이미 팔로우 중입니다."),
    NOT_FOUND_FOLLOW(HttpStatus.NOT_FOUND, "FOLLOW_003", "팔로우 중이 아닙니다.");

    private final HttpStatus status;

    private final String code;

    private final String message;
}
