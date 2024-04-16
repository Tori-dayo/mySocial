package com.dreamflow.mysocial.content.exception;

import com.dreamflow.mysocial.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ContentErrorCode implements ErrorCode {

    NOT_FOUND_CONTENT(HttpStatus.NOT_FOUND, "CONTENT_001", "존재하지 않는 컨텐츠 입니다.")
    ;

    private final HttpStatus status;

    private final String code;

    private final String message;

}
