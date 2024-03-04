package com.example.demo.domain;

import com.example.demo.dto.exhibitionDto.ExhibitionRequestDto;
import jakarta.persistence.*;
import jdk.jfr.Description;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
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
    private String main_poster;
    private String address;
    @Column(columnDefinition = "integer default 0")
    private long viewCounts;

    @OneToMany(mappedBy = "exhibition")
    private List<Member> memberList;

    @OneToMany(mappedBy = "exhibition")
    private List<Tag> tagList;

    @OneToMany(mappedBy = "exhibition")
    private List<Work> workList;

    private Date openTime;

    private Date endTime;

    public void updateView(){
        viewCounts += 1;
    }
    public Exhibition updateExhibition(ExhibitionRequestDto.updateExhibition updateExhibition){
        return Exhibition.builder()
                .id(this.id)
                .title(updateExhibition.getTitle() != null ? updateExhibition.getTitle():this.title)
                .introduction(updateExhibition.getIntroduction() != null? updateExhibition.getIntroduction():this.introduction)
                .location_x(updateExhibition.getLocation_x() != null ? updateExhibition.getLocation_x() : this.location_x)
                .location_y(updateExhibition.getLocation_y() != null ? updateExhibition.getLocation_y() : this.location_y)
                .address(updateExhibition.getAddress() != null ? updateExhibition.getAddress() : this.location_y)
                .startDate(updateExhibition.getStartDate() != null? updateExhibition.getStartDate() : this.startDate)
                .endDate(updateExhibition.getEndDate() != null? updateExhibition.getEndDate() : this.endDate)
                .main_poster(updateExhibition.getMain_poster()!= null? updateExhibition.getMain_poster() : this.main_poster)
                .viewCounts(this.viewCounts)
                .build();
    }
}
