package com.dreamflow.mysocial.comment.controller;

import com.dreamflow.mysocial.comment.dto.CommentDto;
import com.dreamflow.mysocial.comment.mapper.CommentMapper;
import com.dreamflow.mysocial.comment.service.CommentService;
import com.dreamflow.mysocial.global.common.BaseResponse;
import com.dreamflow.mysocial.global.common.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController{
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    @PostMapping("/{contentId}")
    public BaseResponse<CommentDto.Response> createComment(@PathVariable("contentId") Long id, @RequestBody CommentDto.Post post, @CurrentUser Long memberId) {
        return new BaseResponse<>(commentMapper.CommentToCommentResponseDto(commentService.createComment(id, commentMapper.CommentPostDtoToComment(post), memberId)));
    }

    @PatchMapping("/{commentId}")
    public BaseResponse<CommentDto.Response> patchComment(@PathVariable("commentId") Long id, @RequestBody CommentDto.Patch patch) {
        return new BaseResponse<>(commentMapper.CommentToCommentResponseDto(commentService.patchComment(id, commentMapper.CommentPatchDtoToComment(patch))));
    }

    @GetMapping("/{contentId}")
    public BaseResponse<List<CommentDto.ResponseList>> findAllComments(@PathVariable("contentId") Long contentId) {
        return new BaseResponse<>(commentMapper.CommentToCommentResponseListDto(commentService.findAllComments(contentId)));
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable("commentId") Long id) {
        commentService.deleteComment(id);
    }
}

