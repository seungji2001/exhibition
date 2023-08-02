package com.example.demo.dto.memberDto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

public class MemberRequestDto {
    @Builder
    @Data
    @Getter
    public static class registNewMember{
        private String name;
        private String major;
        private String introduction;
    }
}
