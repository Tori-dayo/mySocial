package com.dreamflow.mysocial.member.entity;

import com.dreamflow.mysocial.comment.entity.Comment;
import com.dreamflow.mysocial.content.entity.Content;
import com.dreamflow.mysocial.follow.entity.Follow;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;


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

    @Column
    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        ADMIN,
        USER;
    }

    // Member와 Content는 1:N 관계
    @OneToMany(mappedBy = "member")
    private List<Content> contents;

    // Member와 Comment는 1:N 관계
    @OneToMany(mappedBy = "member")
    private List<Comment> comments;

    // Member와 Follow는 1:N 관계 (fromMember)
    @OneToMany(mappedBy = "fromMember")
    private List<Follow> fromMembers;

    // Member와 Follow는 1:N 관계 (toMember)
    @OneToMany(mappedBy = "toMember")
    private List<Follow> toMembers;
}
