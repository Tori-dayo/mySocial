package com.dreamflow.mysocial.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CommentDto {
    @Getter
    @NoArgsConstructor
    public static class Post{
        private String commentContent;
    }
    @Getter
    @NoArgsConstructor
    public static class Patch{
        private String commentContent;
    }
    @Getter @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response{
        private Long id;
        private String commentContent;
    }
    @Getter @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseList{
        private Long id;
        private String commentContent;
    }
}
