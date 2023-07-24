package com.example.demo.repository;

import com.example.demo.domain.Member;
import com.example.demo.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository  extends JpaRepository<Member, Long> {
}
