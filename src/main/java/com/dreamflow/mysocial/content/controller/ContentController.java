package com.dreamflow.mysocial.content.controller;

import com.dreamflow.mysocial.content.dto.ContentDto;
import com.dreamflow.mysocial.content.entity.Content;
import com.dreamflow.mysocial.content.mapper.ContentMapper;
import com.dreamflow.mysocial.content.service.ContentService;
import com.dreamflow.mysocial.global.common.BaseResponse;
import com.dreamflow.mysocial.global.common.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contents")
public class ContentController {
    private final ContentService contentService;
    private final ContentMapper contentMapper;

    //Postman -> Body에서 form-data로 설정 후, [key = post , value = JSON , Content-Type = Application/Json] 으로 설정
    @PostMapping("/create")
    public BaseResponse<ContentDto.Response> createContent(@RequestPart ContentDto.Post post,
                                                           @RequestPart(value = "image", required = false) MultipartFile image,
                                                           @CurrentUser Long id) throws IOException {
        Content content = contentService.createContent(contentMapper.ContentPostDtoToContent(post), image,id);
        return new BaseResponse<>(contentMapper.ContentToContentResponseDto(content));
    }

    @PatchMapping("/patch/{id}")
    public BaseResponse<ContentDto.Response> patchContent(@PathVariable Long id, @RequestBody ContentDto.Patch patch) {
        Content content = contentService.patchContent(id, contentMapper.ContentPatchDtoToContent(patch));
        return new BaseResponse<>(contentMapper.ContentToContentResponseDto(content));
    }

    @GetMapping("/{id}")
    public BaseResponse<ContentDto.Response> findContent(@PathVariable Long id) {
        Content content = contentService.findContent(id);
        return new BaseResponse<>(contentMapper.ContentToContentResponseDto(content));
    }

    @DeleteMapping("/{id}")
    public void deleteContent(@PathVariable Long id) {
        contentService.deleteContent(id);
    }

    @GetMapping("/user")
    public BaseResponse<List<ContentDto.Response>> findUserContents(@CurrentUser Long id) {
        List<Content> userContents = contentService.findFeedContents(id);
        return new BaseResponse<>(contentMapper.ContentToListResponseDto(userContents));
    }
}
