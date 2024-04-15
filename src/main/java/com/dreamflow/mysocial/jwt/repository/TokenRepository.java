package com.dreamflow.mysocial.jwt.repository;

import com.dreamflow.mysocial.jwt.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {
    Optional<Token> findByAccessToken(String accessToken);
    void deleteByMemId(Long memId);
}
