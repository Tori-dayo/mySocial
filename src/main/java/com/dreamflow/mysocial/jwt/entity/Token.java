package com.dreamflow.mysocial.jwt.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name= "token")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private Long memId;
    private String accessToken;
    private String refreshToken;
    private int mode;

    public Token(String accessToken, String refreshToken){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public void setMemId(Long memId){
        this.memId = memId;
    }

    public void setMode(int mode){
        this.mode = mode;
    }
}
