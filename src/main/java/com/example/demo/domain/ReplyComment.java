package com.example.demo.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value={"modifiedDate"}, allowGetters=true)
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

    //수정의 경우 1로
    @ColumnDefault("0")
    private Integer modified;

    @PostLoad
    public void checkModified(){
        if(!insertDate.equals(modifiedDate)){
            modified = 1;
        }
    }

    public void updateReplyComment(String content){
        this.content = content;
    }

}
