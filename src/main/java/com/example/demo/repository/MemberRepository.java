package com.example.demo.repository;

import com.example.demo.Enum.MemberRole;
import com.example.demo.domain.Exhibition;
import com.example.demo.domain.Member;
import com.example.demo.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository  extends JpaRepository<Member, Long> {

    Optional<Member> findMemberByName(String name);
    Optional<Member> findMemberByLoginIdAndPassword(String loginId, String password);
    Optional<Member> findMemberByIdAndExhibition(Long memberId, Exhibition exhibition);
    List<Member> findAllByExhibitionAndMemberRole(Exhibition exhibition, MemberRole memberRole);

}
