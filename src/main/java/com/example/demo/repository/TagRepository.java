package com.example.demo.repository;

import com.example.demo.domain.Exhibition;
import com.example.demo.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}
