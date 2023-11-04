package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    private Long id;

    //작품번호
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_id")
    private Work work;

    //댓글 단 사람의 id -> 작가 + 관람자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //내용
    private String content;

    //댓글 단 일자
    @CreationTimestamp
    private LocalDateTime insertDate;

    //수정일자
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @OneToMany(mappedBy = "comment")
    private List<ReplyComment> replyCommentList;

    //수정된 경우 true로
    private Boolean modified;

    private void checkModified(){
        if(insertDate != modifiedDate){
            modified = true;
        }
    }
}
