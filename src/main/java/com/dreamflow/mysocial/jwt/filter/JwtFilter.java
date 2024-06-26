package com.dreamflow.mysocial.jwt.filter;

import com.dreamflow.mysocial.jwt.entity.Token;
import com.dreamflow.mysocial.jwt.provider.TokenProvider;
import com.dreamflow.mysocial.jwt.repository.TokenRepository;
import com.dreamflow.mysocial.member.entity.Member;
import com.dreamflow.mysocial.member.repository.MemberRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private final TokenProvider tokenProvider;

    private final TokenRepository tokenRepository;

    private final MemberRepository memberRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = resolveToken(request);
        String requestURI = (request).getRequestURI();

        if (StringUtils.hasText(jwt)) {
            Token token = tokenRepository.findByAccessToken(jwt).orElseThrow(() -> new RuntimeException("토큰이 존재하지 않습니다."));
            Member member = memberRepository.findById(token.getMemId()).orElseThrow(() -> new RuntimeException("멤버가 존재하지 않습니다."));
            if (member != null) {
                Authentication authentication = tokenProvider.getAuthentication(jwt);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                throw new RuntimeException("멤버가 존재하지 않습니다.");
            }
        } else {
            logger.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI);
        }


        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }

}
