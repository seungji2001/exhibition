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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Transactional
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
                .writer(member.getName())
                .content(updateComment.getContent())
                .build();
    }

    @Transactional
    public List<CommentResponseDto.GetCommentsResponse> getComments(Long work_id, Pageable pageable){
        Work work = workRepository.findById(work_id).orElseThrow();
        Page<Comment> comments = commentRepository.findAllByWorkOrderByInsertDateDesc(work,pageable);
        List<CommentResponseDto.GetCommentsResponse> getCommentsResponses = new ArrayList<>();

        if(comments.isEmpty()){
            return getCommentsResponses;
        }
        getCommentsResponses.addAll(
            comments.stream()
                    .map(comment -> {
                        long count = (long) replyCommentRepository.findAllByComment(comment).size();
                        return CommentResponseDto.GetCommentsResponse.builder()
                                .comment_id(comment.getId())
                                .content(comment.getContent())
                                .writer(comment.getMember().getName())
                                .insertDate(comment.getInsertDate())
                                .modifiedDate(comment.getModifiedDate())
                                .modified(comment.getModified())
                                .replyCommentCount((int) count)
                                .build();
                    })
                    .toList()
            );
        return getCommentsResponses;
    }

    @Transactional
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

    @Transactional
    public List<CommentResponseDto.GetReplyCommentsResponse> getReplyComment(Long comment_id){
        Comment comment = commentRepository.findById(comment_id).orElseThrow();
        List<ReplyComment> replyComments = replyCommentRepository.findAllByComment(comment);

        List<CommentResponseDto.GetReplyCommentsResponse> getReplyCommentsResponses = new ArrayList<>();

        if(replyComments.isEmpty()){
            return getReplyCommentsResponses;
        }
        getReplyCommentsResponses.addAll(
                replyComments.stream()
                        .map(replyComment -> {
                            return CommentResponseDto.GetReplyCommentsResponse.builder()
                                    .reply_comment_id(replyComment.getId())
                                    .content(replyComment.getContent())
                                    .writer(replyComment.getMember().getName())
                                    .insertDate(replyComment.getInsertDate())
                                    .modifiedDate(replyComment.getModifiedDate())
                                    .modified(replyComment.getModified())
                                    .build();
                        })
                        .toList()
        );
        return getReplyCommentsResponses;
    }

    @Transactional
    public CommentResponseDto.GetReplyCommentResponse updateReplyComment(CommentRequestDto.UpdateReplyComment updateReplyComment){
        Member member = memberRepository.findById(updateReplyComment.getMember_id()).orElseThrow();
        ReplyComment replyComment = replyCommentRepository.findById(updateReplyComment.getReply_comment_id()).orElseThrow();

        //작성한 멤버만 수정이 가능 하다
        if(!replyComment.getMember().equals(member))
            throw new IllegalArgumentException();

        //변경 사항이 없으면 수정 안한다
        if(replyComment.getContent().equals(updateReplyComment.getContent()))
            throw new IllegalArgumentException("변경사항이 없습니다.");

        replyComment.updateReplyComment(updateReplyComment.getContent());
        replyCommentRepository.save(replyComment);

        return CommentResponseDto.GetReplyCommentResponse.builder()
                .reply_comment_id(updateReplyComment.getReply_comment_id())
                .writer(member.getName())
                .content(updateReplyComment.getContent())
                .build();
    }
}
