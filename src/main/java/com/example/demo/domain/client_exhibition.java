package com.example.demo.domain;

import com.example.demo.Enum.MemberRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "client_exhibition")
public class client_exhibition {
    @Id
    @Column(name = "client_exhibition_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exhitbition_id")
    private Exhibition exhibition;

    private MemberRole memberRole;
}
