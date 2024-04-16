package com.dreamflow.mysocial.member.controller;

import com.dreamflow.mysocial.jwt.entity.Token;
import com.dreamflow.mysocial.member.dto.MemberDto;
import com.dreamflow.mysocial.member.entity.Member;
import com.dreamflow.mysocial.member.mapper.MemberMapper;
import com.dreamflow.mysocial.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;

    @PostMapping("/sign-up")
    public ResponseEntity<Member> signUp(@RequestBody MemberDto.SignUpRequest signUpRequest) {
        return ResponseEntity.ok(memberService.signUp(signUpRequest));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Token> signIn(@RequestBody MemberDto.SignInRequest signInRequest) {
        return ResponseEntity.ok(memberService.signIn(signInRequest));
    }

    @PostMapping("/sign-out")
    public ResponseEntity<Long> signOut(@RequestBody Long id) {
        return ResponseEntity.ok(memberService.signOut(id));
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<Long> withdrawal(@RequestBody Long id) {
        return ResponseEntity.ok(memberService.withdrawal(id));
    }

}
