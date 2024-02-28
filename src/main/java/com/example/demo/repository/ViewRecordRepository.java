package com.example.demo.repository;

import com.example.demo.domain.ViewRecord;
import com.example.demo.domain.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewRecordRepository extends JpaRepository<ViewRecord, Long> {
    List<ViewRecord> findAllByWork(Work work);
}
