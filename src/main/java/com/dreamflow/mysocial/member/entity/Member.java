package com.dreamflow.mysocial.member.entity;

import com.dreamflow.mysocial.comment.entity.Comment;
import com.dreamflow.mysocial.content.entity.Content;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;


@Entity @Builder
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {
    private static final String PASSWORD_PATTERN = "^(?=.*[a-zA-Z])(?=.*[!@#$%^&*+=-])(?=.*[0-9]).{8,12}$";
    public static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

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



    public static String encrypt(String value, PasswordEncoder encoder) {
        validatePassword(value);
        return encoder.encode(value);
    }

    private static void validatePassword(String value) {
        if (isNotValidPattern(value)) {
            throw new RuntimeException();
        }
    }

    private static boolean isNotValidPattern(String password) {
        return !password.matches(PASSWORD_PATTERN);
    }

    @OneToMany(mappedBy = "member")
    private List<Content> contents;

    @OneToMany(mappedBy = "member")
    private List<Comment> comments;
}
