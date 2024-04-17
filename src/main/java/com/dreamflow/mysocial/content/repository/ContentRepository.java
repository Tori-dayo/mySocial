package com.dreamflow.mysocial.content.repository;

import com.dreamflow.mysocial.content.entity.Content;
import com.dreamflow.mysocial.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> findByMemberId(Long memberId);
    List<Content> findByMember(Member member);
}
