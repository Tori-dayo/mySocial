package com.dreamflow.mysocial.content.service;

import com.dreamflow.mysocial.content.entity.Content;
import com.dreamflow.mysocial.content.exception.ContentErrorCode;
import com.dreamflow.mysocial.content.repository.ContentRepository;
import com.dreamflow.mysocial.global.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ContentService {
    private final ContentRepository contentRepository;

    public Content createContent(Content content, MultipartFile image) throws IOException {
        return contentRepository.save(content);
    }

    public Content patchContent(Long id, Content content) {
        Content findContent = findVerifiedContent(id);
        findContent.setContent(content.getContent());
        findContent.setTitle(content.getTitle());
        findContent.setImageUrl(content.getImageUrl());
        return contentRepository.save(findContent);
    }

    public Content findContent(Long id) {
        return findVerifiedContent(id);
    }

    public List<Content> findAllContents() {
        //멤버만
        return contentRepository.findAll();
    }

    public void deleteContent(Long id) {
        contentRepository.deleteById(id);
    }

    public Content findVerifiedContent(Long id) {
        return contentRepository.findById(id)
                .orElseThrow(() -> BaseException.type(ContentErrorCode.NOT_FOUND_CONTENT));
    }
}