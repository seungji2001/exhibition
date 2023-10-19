package com.example.demo.dto.ChatBotDto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ChatBotResponseDto {
    @Builder
    @Data
    public static class ResponseAnswer {
        private String answer;
    }
}
