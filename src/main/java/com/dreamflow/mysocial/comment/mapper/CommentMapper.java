package com.dreamflow.mysocial.comment.mapper;

import com.dreamflow.mysocial.comment.dto.CommentDto;
import com.dreamflow.mysocial.comment.entity.Comment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment CommentPostDtoToComment(CommentDto.Post post);
    Comment CommentPatchDtoToComment(CommentDto.Patch patch);
    CommentDto.Response CommentToCommentResponseDto(Comment comment);
    List<CommentDto.ResponseList> CommentToCommentResponseListDto(List<Comment> comments);
}

