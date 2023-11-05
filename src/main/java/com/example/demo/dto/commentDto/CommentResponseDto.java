package com.example.demo.dto.commentDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class GetReplyCommentResponse{
        Long comment_id;
        String writer;
        String content;
        LocalDateTime insertDate;
        LocalDateTime modifiedDate;
        Integer modified;
    }
}
