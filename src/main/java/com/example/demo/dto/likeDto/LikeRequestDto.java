package com.example.demo.dto.likeDto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

public class LikeRequestDto {

    @Builder
    @Data
    @Getter
    public static class createLike{
        private Long memberId; // 멤버 id
        private Long workId; // 작품 id
    }
}
