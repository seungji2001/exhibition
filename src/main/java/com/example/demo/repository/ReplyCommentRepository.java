package com.example.demo.repository;

import com.example.demo.domain.Comment;
import com.example.demo.domain.ReplyComment;
import com.example.demo.dto.commentDto.CommentResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyCommentRepository extends JpaRepository<ReplyComment, Long> {
    List<ReplyComment> findAllByComment(Comment comment);
}
