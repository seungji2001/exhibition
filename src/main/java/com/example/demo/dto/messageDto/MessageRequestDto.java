package com.example.demo.dto.messageDto;

import com.example.demo.domain.Member;
import com.example.demo.domain.Work;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

public class MessageRequestDto {
    @Builder
    @Data
    public static class sendMessage{
       private String sender;
       private String contents;
       private String title;
       private String email;
    }
}
