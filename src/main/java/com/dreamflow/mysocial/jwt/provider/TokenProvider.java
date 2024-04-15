package com.dreamflow.mysocial.jwt.provider;

import com.dreamflow.mysocial.jwt.dto.MemberDetails;
import com.dreamflow.mysocial.jwt.dto.UserDetailDto;
import com.dreamflow.mysocial.jwt.entity.Token;
import com.dreamflow.mysocial.member.entity.Member;
import com.dreamflow.mysocial.member.repository.MemberRepository;
import com.dreamflow.mysocial.member.service.MemberTokenService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.dreamflow.mysocial.member.entity.Password.ENCODER;

@Component
@RequiredArgsConstructor
public class TokenProvider implements InitializingBean {

    private final Logger logger = (Logger) LoggerFactory.getLogger(TokenProvider.class);

    private static final String AUTHORITIES_KEY = "auth";

    @Value("${jwt.secret}")
    private String secret;

    @Value("3600000")
    private long tokenValidityInMilliseconds;

    @Value("3600000")
    private long refreshTokenValidityInMilliseconds;

    private final MemberTokenService memberTokenService;

    private final MemberRepository memberRepository;

    private Key key;

    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public Token createToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date accessValidity = new Date(now + this.tokenValidityInMilliseconds);
        Date refreshValidity = new Date(now + this.refreshTokenValidityInMilliseconds);

        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(accessValidity)
                .compact();

        String refreshToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(refreshValidity)
                .compact();

        return new Token(accessToken, refreshToken);
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);
        Member member = memberRepository.findByEmail(principal.getUsername());
        MemberDetails memberDetails = new MemberDetails(new UserDetailDto(member));

        return new UsernamePasswordAuthenticationToken(memberDetails, token, authorities);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            throw BaseException.type(JwtErrorCode.INVALID_TOKEN);
        } catch (ExpiredJwtException e) {
            throw BaseException.type(JwtErrorCode.EXPIRED_TOKEN);
        } catch (UnsupportedJwtException e) {
            throw BaseException.type(JwtErrorCode.UNSUPPORTED_TOKEN);
        } catch (IllegalArgumentException e) {
            throw BaseException.type(JwtErrorCode.ILLEGAL_ARGUMENT);
        }
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(ENCODER);
        provider.setUserDetailsService(memberTokenService);
        return provider;
    }
}
