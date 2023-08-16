package com.example.demo.service;

import com.example.demo.Enum.MemberRole;
import com.example.demo.domain.Member;
import com.example.demo.dto.memberDto.MemberRequestDto;
import com.example.demo.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    MemberRepository memberRepository;

    //어드민 등록
    @Transactional
    public Long registration(MemberRequestDto.adminMember adminMember){
        Member member = Member.builder()
                .loginId(adminMember.getLoginId())
                .password(adminMember.getPassword())
                .name(adminMember.getName())
                .cellphone(adminMember.getCellphone())
                .memberRole(MemberRole.ADMIN)
                .build();

       return memberRepository.save(member).getId();
    }
}
