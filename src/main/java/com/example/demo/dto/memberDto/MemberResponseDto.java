package com.example.demo.dto.memberDto;

import com.example.demo.domain.Work;
import com.example.demo.dto.workDto.WorkResponseDto;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

public class MemberResponseDto {

    @Builder
    @Data
    public static class getMember{
        private Long id;
        private String name;
        private String major;
        private Work mainWork;
        private String introduction;
    }

    @Builder
    @Data
    @Getter
    public static class getWorksByMember{
        private String mainWorkTitle;
        private String mainWorkContents;
        private List<WorkResponseDto.getWork> getWorkList;
    }

    @Builder
    @Data
    public static class getSupporterMember{
        private Long id;
        private String loginId;
        private String name;
        private String introduction;
        private String cellphone;
        private String major;
    }

    @Builder
    @Data
    public static class getParticipants{
        private Long id;
        private String name;
    }
}
