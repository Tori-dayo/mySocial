package com.dreamflow.mysocial.follow.repository;

import com.dreamflow.mysocial.follow.entity.Follow;
import com.dreamflow.mysocial.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long>{
    Optional<Follow> findByFromMemberAndToMember(Member fromMember, Member toMember);
    List<Follow> findByFromMember(Member fromMember);
    List<Follow> findByToMember(Member toMember);
}
