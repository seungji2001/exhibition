package com.example.demo.service;

import com.example.demo.Enum.MemberRole;
import com.example.demo.domain.Exhibition;
import com.example.demo.domain.Member;
import com.example.demo.dto.memberDto.MemberRequestDto;
import com.example.demo.repository.ExhibitionRepository;
import com.example.demo.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupporterService {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ExhibitionRepository exhibitionRepository;

    @Transactional
    public Long registration(MemberRequestDto.supporterMember supporterMember, Long exhibitionId){
        Exhibition exhibition = exhibitionRepository.findById(exhibitionId).orElseThrow(()-> new IllegalArgumentException("전시를 먼저 등록해주세요"));
        Member member = Member.builder()
                .loginId(supporterMember.getLoginId())
                .password(supporterMember.getPassword())
                .name(supporterMember.getName())
                .cellphone(supporterMember.getCellphone())
                .introduction(supporterMember.getIntroduction())
                .major(supporterMember.getMajor())
                .exhibition(exhibition)
                .memberRole(MemberRole.SUPPORTER)
                .build();

        return memberRepository.save(member).getId();
    }
}
