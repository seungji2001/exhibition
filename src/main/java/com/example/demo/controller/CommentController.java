package com.example.demo.controller;

import com.example.demo.dto.RequestDto;
import com.example.demo.dto.commentDto.CommentRequestDto;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    //댓글 등록
    @PostMapping(value = "/comment")
    public ResponseEntity<Long> home(@RequestBody CommentRequestDto.RegistrationComment registrationComment) {
        return ResponseEntity.ok().body(commentService.saveComment(registrationComment));
    }
}
