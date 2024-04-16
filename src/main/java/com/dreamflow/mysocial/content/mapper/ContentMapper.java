package com.dreamflow.mysocial.content.mapper;

import com.dreamflow.mysocial.content.dto.ContentDto;
import com.dreamflow.mysocial.content.entity.Content;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContentMapper {
    Content ContentPostDtoToContent(ContentDto.Post contentDto);
    Content ContentPatchDtoToContent(ContentDto.Patch contentDto);
    ContentDto.Response ContentToContentResponseDto(Content content);
    List<ContentDto.Response> ContentToListResponseDto(List<Content> contents);
}

