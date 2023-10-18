package com.example.demo.dto.likeDto;

import lombok.*;


public class LikeRequestDto {

    @Builder
    @Data
    public static class createLike{
        private Long memberId; // 멤버 id
        private Long workId; // 작품 id
    }
}
