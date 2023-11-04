package com.example.demo.dto.commentDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CommentResponseDto {
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class GetCommentResponse{
        String comment_id;
        String content;
    }
}
