package com.dreamflow.mysocial.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class PostDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post{
        private String title;
        private String content;
        private String imageUrl;
    }
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Patch{
        private String title;
        private String content;
        private String imageUrl;
    }
//    @Getter
//    @Setter
//    @NoArgsConstructor
//    @AllArgsConstructor
//    public static class Response{
//
//    }
}
