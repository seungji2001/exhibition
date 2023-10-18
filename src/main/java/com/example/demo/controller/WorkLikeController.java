package com.example.demo.controller;

import com.example.demo.dto.likeDto.CreateLikeDTO;
import com.example.demo.dto.likeDto.LikeRequestDto;
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
    public Long createLike(@RequestBody CreateLikeDTO createLikeDTO){
//        return ResponseEntity.ok().body(likeService.createLike(createLikeDTO));
        return likeService.createLike(createLikeDTO);
//        return createLikeDTO;
    }


    @DeleteMapping("/delete/{member_id}/{work_id}")
    public String deleteLike(@PathVariable("member_id") Long member_id, @PathVariable("work_id") Long work_id){
        String deleteLike = likeService.deleteLike(member_id, work_id);
        return deleteLike;
    }

    @GetMapping("/count/{work_id}")
    public Long countLike(@PathVariable(value = "work_id") Long work_id){
        return likeService.countLike(work_id);
    }


}
