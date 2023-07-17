package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;


public class ResponseDto {

    @Builder
    @Data
    public static class getParticipantsRecords{
        private String writer;
        private String content;

    }
}
