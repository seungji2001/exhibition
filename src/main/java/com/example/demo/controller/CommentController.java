package com.example.demo.controller;

import com.example.demo.dto.RequestDto;
import com.example.demo.dto.commentDto.CommentRequestDto;
import com.example.demo.dto.commentDto.CommentResponseDto;
import com.example.demo.service.CommentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    //댓글 등록
    @PostMapping(value = "/comment")
    public ResponseEntity<Long> saveComment(@RequestBody CommentRequestDto.RegistrationComment registrationComment) {
        return ResponseEntity.ok().body(commentService.saveComment(registrationComment));
    }

    //댓글 수정
    @PutMapping(value = "/comment")
    public ResponseEntity<CommentResponseDto.GetCommentResponse> updateComment(@RequestBody CommentRequestDto.UpdateComment updateComment) {
        return ResponseEntity.ok().body(commentService.updateComment(updateComment));
    }

    @GetMapping(value = "/comments/{work_id}")
    public ResponseEntity<List<CommentResponseDto.GetCommentsResponse>> getComments(@PathVariable("work_id")Long work_id){
        return ResponseEntity.ok().body(commentService.getComments(work_id));
    }

    //대댓글 등록
    @PostMapping(value = "/replyComment/{comment_id}")
    public ResponseEntity<Long> saveReplyComment(@PathVariable("comment_id")Long comment_id, @RequestBody CommentRequestDto.RegistrationReplyComment registrationReplyComment) {
        return ResponseEntity.ok().body(commentService.saveReplyComment(comment_id, registrationReplyComment));
    }

    // 대댓글 수정
    @PutMapping(value = "/replyComment")
    public ResponseEntity<CommentResponseDto.GetReplyCommentResponse> updateReplyComment(@RequestBody CommentRequestDto.UpdateReplyComment updateReplyComment){
        return ResponseEntity.ok().body(commentService.updateReplyComment(updateReplyComment));
    }

    // 대댓글 불러오기
    @GetMapping(value = "/replyComments/{comment_id}")
    public ResponseEntity<List<CommentResponseDto.GetReplyCommentsResponse>> getReplyComment(@PathVariable("comment_id")Long comment_id){
        return ResponseEntity.ok().body(commentService.getReplyComment(comment_id));
    }

}
