package com.dreamflow.mysocial.content.service;

import com.dreamflow.mysocial.content.entity.Content;
import com.dreamflow.mysocial.content.exception.ContentErrorCode;
import com.dreamflow.mysocial.content.repository.ContentRepository;
import com.dreamflow.mysocial.follow.repository.FollowRepository;
import com.dreamflow.mysocial.global.exception.BaseException;
import com.dreamflow.mysocial.member.exception.MemberErrorCode;
import com.dreamflow.mysocial.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ContentService {
    private final ContentRepository contentRepository;
    private final MemberRepository memberRepository;
    private final FollowRepository followRepository;

    public Content createContent(Content content, MultipartFile image, Long id) throws IOException {
        content.setMember(memberRepository.findById(id).orElseThrow(()->new BaseException(MemberErrorCode.NOT_FOUND_MEMBER)));
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

//    public List<Content> findAllContents(Long memberId) {
//
//    }

    public void deleteContent(Long id) {
        contentRepository.deleteById(id);
    }

    public Content findVerifiedContent(Long id) {
        return contentRepository.findById(id)
                .orElseThrow(() -> BaseException.type(ContentErrorCode.NOT_FOUND_CONTENT));
    }
}