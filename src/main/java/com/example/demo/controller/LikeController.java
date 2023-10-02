package com.example.demo.controller;

import com.example.demo.dto.likeDto.LikeRequestDto;
import com.example.demo.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/like")
public class LikeController {

    private final LikeService likeService;

    // 좋아요 달기
    @PostMapping("/create")
    public ResponseEntity<Long> createLike(LikeRequestDto.createLike createLikeDTO){
        return ResponseEntity.ok().body(likeService.createLike(createLikeDTO));
    }
}
