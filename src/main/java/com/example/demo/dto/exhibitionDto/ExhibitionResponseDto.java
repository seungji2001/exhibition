package com.example.demo.dto.exhibitionDto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

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
        private String main_poster;
    }
}
