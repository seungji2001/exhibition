package com.example.demo.domain;

import com.example.demo.dto.workDto.WorkRequestDto;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "work_id")
    private Long id;

    private String title;

    private String ImgUrl;

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
