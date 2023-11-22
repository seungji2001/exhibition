package com.example.demo.repository;

import com.example.demo.domain.Exhibition;
import com.example.demo.domain.Work;
import com.example.demo.dto.workDto.GetAllWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {
    Optional<Work> findById(Long workId);

    List<GetAllWork> findByExhibition(Exhibition exhibition);

}
