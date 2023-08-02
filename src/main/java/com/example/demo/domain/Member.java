package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="member_id")
    private Long id;

    private String name;

    private String major;

    @OneToMany(mappedBy = "member")
    private List<Work> workList;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_work_id", referencedColumnName = "work_id")
    private Work mainWork;

    private String introduction;

    @OneToMany(mappedBy = "receiver")
    private List<Message> messageList;

    public void setMainWork(Work work){
        this.mainWork = work;
    }
}
