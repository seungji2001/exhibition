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
@Entity(name = "exhibition")
public class Exhibition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="exhibition_id")
    private Long id;

    private String title;

    private String location_x;

    private String location_y;

    private String introduction;
    private String startDate;
    private String endDate;

    @Column(columnDefinition = "integer default 0")
    private long viewCounts;

    @OneToMany(mappedBy = "exhibition")
    private List<Member> memberList;

    @OneToMany(mappedBy = "exhibition")
    private List<Tag> tagList;
}
