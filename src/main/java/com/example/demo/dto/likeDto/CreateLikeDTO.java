package com.example.demo.dto.likeDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLikeDTO {

    private Long memberId; // 멤버 id
    private Long workId; // 작품 id
}
