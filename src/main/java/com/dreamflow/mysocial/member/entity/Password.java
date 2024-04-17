package com.dreamflow.mysocial.member.entity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Password {
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
