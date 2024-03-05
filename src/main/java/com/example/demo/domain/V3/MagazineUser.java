package com.example.demo.domain.V3;

import com.example.demo.type.MUserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_magazine_user")
public class MagazineUser {
    @Id
    @Column(name = "tb_magazine_user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Magazine magazine;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    private MUserType mUserType;

    private String applicant;

    public void updateApplicant(String applicant){
        this.applicant = applicant;
    }
}
