package com.example.demo.dto.adminDto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

public class AdminRequestDto {
    @Builder
    @Data
    public static class registExhibition{
        private Long exhibitionId;
        private String title;
        private String introduction;
        private String location_x;
        private String location_y;
        private String startDate;
        private String endDate;
    }
}
