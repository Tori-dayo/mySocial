package com.dreamflow.mysocial.member.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


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


    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        ADMIN,
        USER;
    }

    private static final String PASSWORD_PATTERN = "^(?=.*[a-zA-Z])(?=.*[!@#$%^&*+=-])(?=.*[0-9]).{8,12}$";

    public static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

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





}
