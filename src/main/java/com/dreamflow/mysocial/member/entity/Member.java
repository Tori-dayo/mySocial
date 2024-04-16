package com.dreamflow.mysocial.member.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity @Builder
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String email;

    @Embedded
    private Password password;

    private String name;

    private String role;

    private Authority authority;


}
