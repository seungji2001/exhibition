package com.example.demo.dto.V3;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

public class MagazineDto {
    @Builder
    @Getter
    public static class SaveMagazineDto{
        private String title;
        private String mainPostUrl;
        private String introduction;
        private String plannerName;
        private String plannerIntroduction;
    }

    @Builder
    @Getter
    public static class getMagazineDto{
        private String title;
        private String mainPostUrl;
        private String introduction;
        private String plannerName;
        private String plannerIntroduction;
    }
}
