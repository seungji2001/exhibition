package com.example.demo.dto.ChatBotDto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ChatBotRequestDto {
    @NoArgsConstructor
    @Data
    public static class RequestQuestion {
        private String question;
    }
}
