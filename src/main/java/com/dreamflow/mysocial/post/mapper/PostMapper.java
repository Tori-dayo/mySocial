package com.dreamflow.mysocial.post.mapper;

import com.dreamflow.mysocial.post.dto.PostDto;
import com.dreamflow.mysocial.post.entity.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
    Post toEntity(PostDto.Post postDto);
    Post toEntity(PostDto.Patch postDto);

    //PostDto.Response(Post post);

}
