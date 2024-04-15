package com.dreamflow.mysocial.member.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Authority {

    USER("USER", "사용자"),
    ADMIN("ADMIN", "관리자")
    ;

    private final String authority;

    private final String role;

}
