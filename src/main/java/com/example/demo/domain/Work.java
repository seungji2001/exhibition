package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Work {
    @Id
    private Long id;

    private Long sequence;

    private String title;

    private String ImgUrl;

    private String contents;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
