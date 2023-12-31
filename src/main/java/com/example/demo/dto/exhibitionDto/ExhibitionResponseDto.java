package com.example.demo.dto.exhibitionDto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

public class ExhibitionResponseDto {
    @Builder
    @Data
    public static class getExhibition {
        private Long id;
        private String title;
        private String introduction;
        private String location_x;
        private String location_y;
        private String startDate;
        private String endDate;
        private Long viewCounts;
        private String main_poster;
        private String address;
        private Date openTime;
        private Date endTime;
    }
}
