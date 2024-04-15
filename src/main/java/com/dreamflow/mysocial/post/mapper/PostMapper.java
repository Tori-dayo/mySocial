package com.dreamflow.mysocial.post.mapper;

import com.dreamflow.mysocial.post.dto.PostDto;
import com.dreamflow.mysocial.post.entity.Post;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {
        Post postPostDtoToPost(PostDto.Post postDto);
        Post postPatchDtoToPost(PostDto.Patch postDto);

        PostDto.Response postToPostResponseDto(Post post);

        List<PostDto.Response> postsToPostListResponseDto(List<Post> posts);
    }
