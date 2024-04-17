package com.dreamflow.mysocial.follow.entity;

import com.dreamflow.mysocial.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_member", nullable = false)
    private Member fromMember;

    @ManyToOne
    @JoinColumn(name = "to_member", nullable = false)
    private Member toMember;
}
