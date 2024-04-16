package com.dreamflow.mysocial.comment.controller;

import com.dreamflow.mysocial.comment.dto.CommentDto;
import com.dreamflow.mysocial.comment.entity.Comment;
import com.dreamflow.mysocial.comment.mapper.CommentMapper;
import com.dreamflow.mysocial.comment.service.CommentService;
import com.dreamflow.mysocial.global.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController{
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    @PostMapping("/{content-id}")
    public BaseResponse<CommentDto.Response> createComment(@PathVariable("content-id") Long id, @RequestBody CommentDto.Post post) {
        return new BaseResponse<>(commentMapper.CommentToCommentResponseDto(commentService.createComment(id, commentMapper.CommentPostDtoToComment(post))));
    }

    @PatchMapping("/{comment-id}")
    public BaseResponse<CommentDto.Response> patchComment(@PathVariable("comment-id") Long id, @RequestBody CommentDto.Patch patch) {
        return new BaseResponse<>(commentMapper.CommentToCommentResponseDto(commentService.patchComment(id, commentMapper.CommentPatchDtoToComment(patch))));
    }

    @GetMapping("/{content-id}")
    public BaseResponse<List<CommentDto.ResponseList>> findAllComments(@PathVariable("content-id") Long contentId) {
        return new BaseResponse<>(commentMapper.CommentToCommentResponseListDto(commentService.findAllComments(contentId)));
    }

    @DeleteMapping("/{comment-id}")
    public void deleteComment(@PathVariable("comment-id") Long id) {
        commentService.deleteComment(id);
    }
}

