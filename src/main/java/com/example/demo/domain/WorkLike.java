package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "worklike")
@EntityListeners(AuditingEntityListener.class)
public class WorkLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="work_like_id")
    private Long id; // 좋아요 id

    @OneToOne
    private Member memberId;

    @ManyToOne
    private Work workId;
}

