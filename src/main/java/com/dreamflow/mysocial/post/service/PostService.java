package com.dreamflow.mysocial.post.service;

import com.dreamflow.mysocial.post.dto.PostDto;
import com.dreamflow.mysocial.post.entity.Post;
import com.dreamflow.mysocial.post.mapper.PostMapper;
import com.dreamflow.mysocial.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post patchPost(Long id, Post post) {
        Post findPost = findVerifiedPost(id);
        findPost.setContent(post.getContent());
        findPost.setTitle(post.getTitle());
        findPost.setImageUrl(post.getImageUrl());
        return postRepository.save(findPost);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public Post getPost(Long id) {
        return findVerifiedPost(id);
    }
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public Post findVerifiedPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }
}
