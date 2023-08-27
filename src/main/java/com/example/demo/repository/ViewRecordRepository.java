package com.example.demo.repository;

import com.example.demo.domain.ViewRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewRecordRepository extends JpaRepository<ViewRecord, Long> {
}
