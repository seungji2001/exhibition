package com.example.demo.repository.V3;

import com.example.demo.domain.V3.Magazine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MagazineRepository extends JpaRepository<Magazine, Long> {
    List<Magazine> findAllByIsPublish(Boolean isPublish);
}
