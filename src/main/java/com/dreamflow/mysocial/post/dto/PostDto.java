package com.dreamflow.mysocial.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class PostDto {
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
