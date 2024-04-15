package com.dreamflow.mysocial.jwt.dto;

import com.dreamflow.mysocial.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class UserDetailDto {
    private Long id;
    private String email;
    private String password;
    private List<String> roles;
    public UserDetailDto(Member member){
        this.id = member.getId();
        this.email = member.getEmail();
        this.password = member.getPassword().getValue();
        this.roles = List.of(member.getRole());
    }
}
