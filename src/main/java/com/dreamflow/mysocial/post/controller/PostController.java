package com.dreamflow.mysocial.post.controller;

import com.dreamflow.mysocial.post.dto.PostDto;
import com.dreamflow.mysocial.post.entity.Post;
import com.dreamflow.mysocial.post.mapper.PostMapper;
import com.dreamflow.mysocial.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final PostMapper mapper;

    @PostMapping("/create")
    public ResponseEntity<PostDto.Response> createPost(@RequestBody PostDto.Post postDto) {
        Post post = postService.createPost(mapper.postPostDtoToPost(postDto));
        return ResponseEntity.ok(mapper.postToPostResponseDto(post));
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<PostDto.Response> patchPost(@RequestBody PostDto.Patch patchDto,
                                                      @PathVariable Long id) {
        Post post = postService.patchPost(id,mapper.postPatchDtoToPost(patchDto));
        return ResponseEntity.ok(mapper.postToPostResponseDto(post));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PostDto.Response> getPost(@PathVariable Long id) {
        Post post = postService.getPost(id);
        return ResponseEntity.ok(mapper.postToPostResponseDto(post));
    }

    @GetMapping("/get")
    public ResponseEntity<List<PostDto.Response>> getPosts() {
        return ResponseEntity.ok(mapper.postsToPostListResponseDto(postService.getPosts()));
    }


}
