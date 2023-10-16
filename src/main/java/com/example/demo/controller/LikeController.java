package com.example.demo.controller;

import com.example.demo.dto.likeDto.LikeRequestDto;
import com.example.demo.service.WorkLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/like")
public class LikeController {

    private final WorkLikeService likeService;

    // 좋아요 달기
    @PostMapping("/create")
    public ResponseEntity<Long> createLike(LikeRequestDto.createLike createLikeDTO){
        return ResponseEntity.ok().body(likeService.createLike(createLikeDTO));
    }

    @DeleteMapping("/delete/{member_id}/{work_id}")
    public String deleteLike(@PathVariable("member_id") Long member_id, @PathVariable("work_id") Long work_id){
        String deleteLike = likeService.deleteLike(member_id, work_id);
        return deleteLike;
    }

    @GetMapping("/count/{work_id}")
    public ResponseEntity<Long> countLike(@PathVariable("work_id") Long work_id){
        return ResponseEntity.ok().body(likeService.countLike(work_id));
    }

}
