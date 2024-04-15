package com.dreamflow.mysocial.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@RequiredArgsConstructor
@Embeddable
public class Password {

    private static final String PASSWORD_PATTERN = "^(?=.*[a-zA-Z])(?=.*[!@#$%^&*+=-])(?=.*[0-9]).{8,12}$";

    public static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

    @Column(nullable = false)
    private String value;

    private Password(String value) {
        this.value = value;
    }

    public static Password encrypt(String value, PasswordEncoder encoder) {
        validatePassword(value);
        return new Password(encoder.encode(value));
    }

    private static void validatePassword(String value) {
        if (isNotValidPattern(value)) {
            throw new RuntimeException();
        }
    }

    private static boolean isNotValidPattern(String password) {
        return !password.matches(PASSWORD_PATTERN);
    }

    public boolean isSamePassword(String password, PasswordEncoder encoder) {
        return encoder.matches(password, this.value);
    }

}
