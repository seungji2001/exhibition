package com.example.demo.dto;

import com.example.demo.domain.Member;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;


public class ResponseDto {

    @Builder
    @Data
    public static class getParticipantsRecords{
        private String writer;
        private String content;

    }

    @Builder
    @Data
    public static class getWorks{
        private String title;
        private String ImgUrl;
        private String contents;
        private Member member;
    }
}
