package com.example.demo.service;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Member;
import com.example.demo.domain.ReplyComment;
import com.example.demo.domain.Work;
import com.example.demo.dto.commentDto.CommentRequestDto;
import com.example.demo.dto.commentDto.CommentResponseDto;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ReplyCommentRepository;
import com.example.demo.repository.WorkRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public CommentResponseDto.GetCommentResponse updateComment(CommentRequestDto.UpdateComment updateComment){
        Member member = memberRepository.findById(updateComment.getMember_id()).orElseThrow();
        Comment comment = commentRepository.findById(updateComment.getComment_id()).orElseThrow();

        //작성한 멤버만 수정이 가능 하다
        if(!comment.getMember().equals(member))
            throw new IllegalArgumentException();

        //변경 사항이 없으면 수정 안한다
        if(comment.getContent().equals(updateComment.getContent()))
            throw new IllegalArgumentException("변경사항이 없습니다.");

        comment.updateComment(updateComment.getContent());
        commentRepository.save(comment);

        return CommentResponseDto.GetCommentResponse.builder()
                .comment_id(updateComment.getComment_id())
                .content(updateComment.getContent())
                .build();
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
