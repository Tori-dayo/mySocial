package com.dreamflow.mysocial.comment.service;

import com.dreamflow.mysocial.comment.entity.Comment;
import com.dreamflow.mysocial.comment.exception.CommentErrorCode;
import com.dreamflow.mysocial.comment.repository.CommentRepository;
import com.dreamflow.mysocial.content.service.ContentService;
import com.dreamflow.mysocial.global.exception.BaseException;
import com.dreamflow.mysocial.member.service.MemberService;
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
    private final MemberService memberService;
    public Comment createComment(Long id, Comment comment,Long memberId) {
        Comment newComment = Comment.builder()
                .content(contentService.findVerifiedContent(id))
                .member(memberService.findVerifiedMember(memberId))
                .commentContent(comment.getCommentContent())
                .build();
        return commentRepository.save(newComment);
    }

    public Comment patchComment(Long id, Comment comment) {
        Comment findComment = findVerifiedComment(id);
        findComment.setCommentContent(comment.getCommentContent());
        return commentRepository.save(findComment);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    public List<Comment> findAllComments(Long contentId) {
        return commentRepository.findByContentId(contentId);
    }

    public Comment findVerifiedComment(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> BaseException.type(CommentErrorCode.NOT_FOUND_COMMENT));
    }
}

