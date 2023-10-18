package com.example.demo.dto.likeDto;

import lombok.*;

//@AllArgsConstructor
@NoArgsConstructor
public class LikeRequestDto {

    @Builder
    @Data
    @Getter
    @Setter
    public static class createLike{
        private Long memberId; // 멤버 id
        private Long workId; // 작품 id
    }
}
