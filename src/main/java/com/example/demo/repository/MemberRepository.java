package com.example.demo.repository;

import com.example.demo.domain.Member;
import com.example.demo.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository  extends JpaRepository<Member, Long> {

    Optional<Member> findMemberByName(String name);
    Optional<Member> findMemberByLoginIdAndPassword(String loginId, String password);

}
