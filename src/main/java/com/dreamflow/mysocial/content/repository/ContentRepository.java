package com.dreamflow.mysocial.content.repository;

import com.dreamflow.mysocial.content.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, Long> {
}
