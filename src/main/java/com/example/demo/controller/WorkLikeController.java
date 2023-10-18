package com.example.demo.controller;

import com.example.demo.dto.likeDto.CreateLikeDTO;
import com.example.demo.dto.likeDto.LikeRequestDto;
import com.example.demo.dto.likeDto.LikeResponseDto;
import com.example.demo.service.WorkLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/like")
public class WorkLikeController {

    private final WorkLikeService likeService;

    // 좋아요 달기
    @PostMapping("/create")
    public ResponseEntity<Long> createLike(@RequestBody LikeRequestDto.createLike createLike){
//        return ResponseEntity.ok().body(likeService.createLike(createLikeDTO));
        return ResponseEntity.ok().body(likeService.createLike(createLike));
//        return createLikeDTO;
    }


    @DeleteMapping("/delete/{member_id}/{work_id}")
    public ResponseEntity<String> deleteLike(@PathVariable("member_id") Long member_id, @PathVariable("work_id") Long work_id){
        return ResponseEntity.ok().body(likeService.deleteLike(member_id, work_id));
    }

    @GetMapping("/count/{work_id}")
    public ResponseEntity<Long> countLike(@PathVariable(value = "work_id") Long work_id){
        return ResponseEntity.ok().body(likeService.countLike(work_id));
    }


}
