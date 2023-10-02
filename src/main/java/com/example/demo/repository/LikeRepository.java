package com.example.demo.repository;

import com.example.demo.domain.LikeEntity;
import com.example.demo.domain.Member;
import com.example.demo.domain.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
    Optional<LikeEntity> findByLikeId(Long likeId);
    Optional<LikeEntity> findByMemberIdAndWorkId(Member member, Work work);
    Long countByWorkId(Work work);
}
