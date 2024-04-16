package com.dreamflow.mysocial.content.dto;

import com.dreamflow.mysocial.comment.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class ContentDto {
    @Getter
    @AllArgsConstructor
    public static class Post{
        private String title;
        private String content;
        private String imageUrl;
    }
    @Getter
    @AllArgsConstructor
    public static class Patch{
        private String title;
        private String content;
        private String imageUrl;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response{
        private String title;
        private String content;
        private String imageUrl;
        private List<CommentDto.ResponseList> comments;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ListResponse{
        private String title;
        private String content;
        private String imageUrl;
    }
}
