package com.example.demo.repository;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByWorkOrderByInsertDateDesc(Work work);
}
