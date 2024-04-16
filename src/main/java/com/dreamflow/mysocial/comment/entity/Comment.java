package com.dreamflow.mysocial.comment.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String commentContent;

//    @ManyToOne
//    @JoinColumn(name = "content_id")
//    private Content content;
//
//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private Member member;
}
