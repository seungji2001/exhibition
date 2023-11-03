package com.example.demo.service;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Member;
import com.example.demo.domain.Work;
import com.example.demo.dto.commentDto.CommentRequestDto;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.MemberRepository;
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
}
