package com.example.demo.dto;

import com.example.demo.domain.Member;
import com.example.demo.domain.Message;
import com.example.demo.domain.Work;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;


public class ResponseDto {

    @Builder
    @Data
    public static class getParticipantsRecords{
        private String writer;
        private String content;

    }

    @Builder
    @Data
    public static class getmainWorks{
        private String title;
        private String imgUrl;
        private String contents;
        private Member member;
    }
}
