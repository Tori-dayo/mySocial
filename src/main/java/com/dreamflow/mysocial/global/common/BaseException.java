package com.dreamflow.mysocial.global.common;

import com.dreamflow.mysocial.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{
    private final ErrorCode code;

    public BaseException(ErrorCode code) {
        super(code.getMessage());
        this.code = code;
    }

    public static BaseException type(ErrorCode code) {
        return new BaseException(code);
    }
}
