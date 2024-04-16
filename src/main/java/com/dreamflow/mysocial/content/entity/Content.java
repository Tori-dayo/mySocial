package com.dreamflow.mysocial.content.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column
    private String imageUrl;

//    @OneToMany(mappedBy = "content")
//    private List<Comment> comments;
//
//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private Member member;
}

