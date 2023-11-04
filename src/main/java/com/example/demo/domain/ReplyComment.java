package com.example.demo.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "replycomment")
public class ReplyComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="reply_comment_id")
    private Long id;

    //모댓글 아이디
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    //대댓글 내용
    private String content;

    //대댓글 작성자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //대댓글 작성일자
    @CreationTimestamp
    private LocalDateTime insertDate;

    //대댓글 수정일자
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    //수정된 경우 true로
    private Boolean modified;

    private void checkModified(){
        if(insertDate != modifiedDate){
            modified = true;
        }
    }
}
