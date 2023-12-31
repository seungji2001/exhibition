package com.example.demo.domain;

import com.example.demo.dto.workDto.WorkRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "work_id")
    private Long id;

    private String title;

    private String imgUrl;

    private String contents;

    private int likeCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(columnDefinition = "integer default 0")
    private Long view;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exhibition_id")
    private Exhibition exhibition;

    @OneToMany(mappedBy = "work")
    private List<Comment> comments;

    @Column(name = "template_num")
    private Integer templateNum;

    private String topic;

    public void updateView(){
        view += 1;
    }
    public void updateLikeCount(){
        likeCount += 1;
    }
    public void deleteLikeCount(){
        if (this.likeCount > 0){
            likeCount -= 1;
        }
    }

    public void updateWork(WorkRequestDto.changeSupporterWork changeSupporterWork){
        this.title = changeSupporterWork.getTitle();
        this.contents = changeSupporterWork.getContent();
    }
}
