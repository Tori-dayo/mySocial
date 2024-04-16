package com.dreamflow.mysocial.member.mapper;

import com.dreamflow.mysocial.jwt.entity.Token;
import com.dreamflow.mysocial.member.dto.MemberDto;
import com.dreamflow.mysocial.member.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    MemberDto.SignUpResponse memberToSignUpResponse(Member member);
    MemberDto.Response MemberToMemberResponseDto(Member member);
}
