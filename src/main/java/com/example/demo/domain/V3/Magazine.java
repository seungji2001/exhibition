package com.example.demo.domain.V3;

import jakarta.persistence.*;
import jdk.jfr.Description;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_magazine")
public class Magazine {
    @Id
    @Column(name = "tb_magazine_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String mainPostUrl;

    private String introduction;

    @Description("기획자 이름")
    private String plannerName;

    @Description("기획자 한 줄 소개")
    private String plannerIntroduction;
}
