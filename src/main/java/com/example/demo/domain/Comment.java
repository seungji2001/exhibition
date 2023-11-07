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
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@DynamicUpdate
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value={"modifiedDate"}, allowGetters=true)
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

    //수정의 경우 1로
    @ColumnDefault("0")
    private int modified;

    @PostLoad
    public void checkModified(){
        if(!insertDate.equals(modifiedDate)){
            modified = 1;
        }
    }

    public void updateComment(String content){
        this.content = content;
        if (modified!=1){
            modified=1;
        }
    }

}
