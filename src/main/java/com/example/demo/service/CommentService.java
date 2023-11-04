package com.example.demo.service;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Member;
import com.example.demo.domain.ReplyComment;
import com.example.demo.domain.Work;
import com.example.demo.dto.commentDto.CommentRequestDto;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ReplyCommentRepository;
import com.example.demo.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    WorkRepository workRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ReplyCommentRepository replyCommentRepository;

    public Long saveComment(CommentRequestDto.RegistrationComment registrationComment){
        Work work = workRepository.findById(registrationComment.getWork_id()).orElseThrow();
        Member member = memberRepository.findById(registrationComment.getMember_id()).orElseThrow();
        Comment comment = Comment.builder()
                .content(registrationComment.getContent())
                .work(work)
                .member(member)
                .build();
        return commentRepository.save(comment).getId();
    }
    public Long  saveReplyComment(Long comment_id, CommentRequestDto.RegistrationReplyComment registrationReplyComment){
        Member member = memberRepository.findById(registrationReplyComment.getMember_id()).orElseThrow();
        Comment comment = commentRepository.findById(comment_id).orElseThrow();
        ReplyComment replyComment = ReplyComment.builder()
                .comment(comment)
                .content(registrationReplyComment.getContent())
                .member(member)
                .build();
        return replyCommentRepository.save(replyComment).getId();
    }
}
