package com.example.demo.dto.commentDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CommentRequestDto {

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class RegistrationComment{
        private Long work_id;
        private Long member_id;
        private String content;
    }
}
