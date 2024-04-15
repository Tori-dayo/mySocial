package com.dreamflow.mysocial.member.service;

import com.dreamflow.mysocial.jwt.dto.MemberDetails;
import com.dreamflow.mysocial.jwt.dto.UserDetailDto;
import com.dreamflow.mysocial.member.entity.Member;
import com.dreamflow.mysocial.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Component("userDetailsService")
public class MemberTokenService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));

        return new MemberDetails(new UserDetailDto(
                member.getId(),
                member.getEmail(),
                member.getPassword().getValue(),
                List.of(member.getAuthority().getAuthority())
        ));

    }

}
