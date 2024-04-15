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
    private final PostMapper postMapper;
    public Post createPost(PostDto.Post postDto) {
        Post post = postMapper.toEntity(postDto);
        return postRepository.save(post);
    }

    public Post patchPost(Long id, PostDto.Patch postDto) {
        Post post = findVerifiedPost(id);
        post = postMapper.toEntity(postDto);
        return postRepository.save(post);
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
