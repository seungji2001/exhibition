package com.example.demo.domain;

import com.example.demo.Enum.MemberRole;
import jakarta.persistence.*;
import lombok.*;

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
    //추가된곳
    private String loginId;
    private String password;
    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    private String registerDate;

    private String cellphone;

    private Long likeCount;

    @OneToMany(mappedBy = "receiver")
    private List<Message> messageList;

    public void setMainWork(Work work){
        this.mainWork = work;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exhibition_id")
    private Exhibition exhibition;

}
