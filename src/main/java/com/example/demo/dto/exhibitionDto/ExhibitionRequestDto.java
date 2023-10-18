package com.example.demo.dto.exhibitionDto;

import lombok.Builder;
import lombok.Data;

public class ExhibitionRequestDto {
    @Builder
    @Data
    public static class registrationExhibition {
        private Long exhibitionId;
        private String title;
        private String introduction;
        private String location_x;
        private String location_y;
        private String startDate;
        private String endDate;
    }

    @Builder
    @Data
    public static class updateExhibition {
        private String title;
        private String introduction;
        private String location_x;
        private String location_y;
        private String address;
        private String main_poster;
        private String startDate;
        private String endDate;
    }
}
