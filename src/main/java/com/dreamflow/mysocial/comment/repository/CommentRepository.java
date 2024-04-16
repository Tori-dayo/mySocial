package com.dreamflow.mysocial.comment.repository;

import com.dreamflow.mysocial.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    List<Comment> findByContentId(Long contentId);

}
