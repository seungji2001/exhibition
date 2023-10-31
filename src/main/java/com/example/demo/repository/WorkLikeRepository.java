package com.example.demo.repository;


import com.example.demo.domain.Member;
import com.example.demo.domain.Work;
import com.example.demo.domain.WorkLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkLikeRepository extends JpaRepository<WorkLike, Long> {
    Optional<WorkLike> findByMemberIdAndWorkId(Long memberId, Long workId);
    Long countByWorkId(Long workId);
    Optional<WorkLike> deleteByMemberIdAndWorkId(Member member, Work work);
}
