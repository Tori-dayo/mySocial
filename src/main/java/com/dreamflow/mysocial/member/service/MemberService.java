package com.dreamflow.mysocial.member.service;

import com.dreamflow.mysocial.global.exception.BaseException;
import com.dreamflow.mysocial.jwt.entity.Token;
import com.dreamflow.mysocial.jwt.filter.JwtFilter;
import com.dreamflow.mysocial.jwt.provider.TokenProvider;
import com.dreamflow.mysocial.jwt.repository.TokenRepository;
import com.dreamflow.mysocial.member.dto.MemberDto;
import com.dreamflow.mysocial.member.entity.Member;
import com.dreamflow.mysocial.member.exception.MemberErrorCode;
import com.dreamflow.mysocial.member.repository.MemberRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.Objects;

import static com.dreamflow.mysocial.member.entity.Member.ENCODER;
import static com.dreamflow.mysocial.member.entity.Member.Role.USER;

@Service
@Builder
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final TokenRepository tokenRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public Member signUp(MemberDto.SignUpRequest signUpRequest) {
        validateDuplicateMember(signUpRequest.getEmail());
        Member member = Member.builder()
                .email(signUpRequest.getEmail())
                .password(ENCODER.encode(signUpRequest.getPassword()))
                .name(signUpRequest.getName())
                .role(USER)
                .build();
        return memberRepository.save(member);
    }

    public Token signIn(MemberDto.SignInRequest signInRequest) {
        Member findMember = memberRepository.findByEmail(signInRequest.getEmail());
        comparePassword(signInRequest.getPassword(), findMember.getPassword());
        Token token = generateToken(findMember.getId(), findMember.getEmail(), signInRequest.getPassword());
        System.out.println("token = " + token);
        return token;
    }

    public Long signOut(Long id) {
        tokenRepository.deleteById(id);
        return id;
    }

    public Long withdrawal(Long id) {
        memberRepository.deleteById(id);
        tokenRepository.deleteByMemId(id);
        return id;
    }

    private Token generateToken(Long id, String email, String password) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(email, password);

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Token token = tokenProvider.createToken(authentication);
        token.setMemId(id);
        System.out.println("token = " + token);
        tokenRepository.save(token);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + token.getAccessToken());

        return token;
    }

    private void validateDuplicateMember(String email) {
        if (memberRepository.findByEmail(email)!=null) {
            throw BaseException.type(MemberErrorCode.DUPLICATE_EMAIL);
        }
    }

    private void comparePassword(String password, String memPassword) {
        if(!ENCODER.matches(password, memPassword)) {
            throw BaseException.type(MemberErrorCode.PASSWORD_MISMATCH);
        }
    }

    public String getName(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> BaseException.type(MemberErrorCode.NOT_FOUND_MEMBER));
        return member.getName();
    }

    public Member findVerifiedMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> BaseException.type(MemberErrorCode.NOT_FOUND_MEMBER));
    }


}
