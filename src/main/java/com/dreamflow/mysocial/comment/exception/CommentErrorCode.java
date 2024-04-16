package com.dreamflow.mysocial.comment.exception;

import com.dreamflow.mysocial.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommentErrorCode implements ErrorCode {

    NOT_FOUND_COMMENT(HttpStatus.NOT_FOUND, "COMMENT_001", "존재하지 않는 댓글 입니다.")
    ;

    private final HttpStatus status;

    private final String code;

    private final String message;

}

