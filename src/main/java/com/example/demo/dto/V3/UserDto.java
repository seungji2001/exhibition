package com.example.demo.dto.V3;

import lombok.Builder;
import lombok.Getter;

public class UserDto {
    @Builder
    @Getter
    public static class SaveApplication{
        private String applicant;
    }

    @Builder
    @Getter
    public static class getApplicant {
        private String name;
        private String applicant;
    }
}
