package com.example.demo.repository;

import com.example.demo.type.MemberRole;
import com.example.demo.domain.Exhibition;
import com.example.demo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository  extends JpaRepository<Member, Long> {

    Optional<Member> findById(Long memberId);
    Optional<Member> findMemberByName(String name);
    Optional<Member> findMemberByLoginIdAndPassword(String loginId, String password);
    Optional<Member> findMemberByIdAndExhibition(Long memberId, Exhibition exhibition);
    List<Member> findAllByExhibitionAndMemberRole(Exhibition exhibition, MemberRole memberRole);
    Optional<Member> findMemberByIdAndMemberRole(Long memberId, MemberRole memberRole);

    Optional<Member> findByIdAndExhibition(Long id, Exhibition exhibition);

}
