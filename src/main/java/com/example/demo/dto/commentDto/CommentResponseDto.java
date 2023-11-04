package com.example.demo.dto.commentDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

public class CommentResponseDto {
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class GetCommentResponse{
        Long comment_id;
        String content;
    }
}
