package com.example.demo.dto.memberDto;

import com.example.demo.domain.Member;
import com.example.demo.domain.Message;
import com.example.demo.domain.Work;
import com.example.demo.dto.workDto.WorkResponseDto;
import com.example.demo.service.MemberService;
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

}
