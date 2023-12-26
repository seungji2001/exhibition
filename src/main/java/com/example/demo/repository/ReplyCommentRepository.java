package com.example.demo.repository;

import com.example.demo.domain.Comment;
import com.example.demo.domain.ReplyComment;
import com.example.demo.dto.commentDto.CommentResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReplyCommentRepository extends JpaRepository<ReplyComment, Long> {
    Page<ReplyComment> findAllByComment(Comment comment, Pageable pageable);

    @Override
    Optional<ReplyComment> findById(Long aLong);

}
