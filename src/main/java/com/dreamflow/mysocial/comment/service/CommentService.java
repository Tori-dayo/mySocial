package com.dreamflow.mysocial.comment.service;

import com.dreamflow.mysocial.comment.entity.Comment;
import com.dreamflow.mysocial.comment.exception.CommentErrorCode;
import com.dreamflow.mysocial.comment.repository.CommentRepository;
import com.dreamflow.mysocial.content.service.ContentService;
import com.dreamflow.mysocial.global.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ContentService contentService;
    public Comment createComment(Long id, Comment comment) {
        comment.setContent(contentService.findVerifiedContent(id));
        return commentRepository.save(comment);
    }

    public Comment patchComment(Long id, Comment comment) {
        Comment findComment = findVerifiedComment(id);
        findComment.setCommentContent(comment.getCommentContent());
        return commentRepository.save(findComment);
    }

    public Comment findVerifiedComment(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> BaseException.type(CommentErrorCode.NOT_FOUND_COMMENT));
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    public List<Comment> findAllComments(Long contentId) {
        return commentRepository.findByContentId(contentId);
    }

}

