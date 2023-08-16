package com.example.demo.service;

import com.example.demo.Enum.MemberRole;
import com.example.demo.domain.Exhibition;
import com.example.demo.domain.Member;
import com.example.demo.dto.memberDto.MemberRequestDto;
import com.example.demo.dto.memberDto.MemberResponseDto;
import com.example.demo.repository.ExhibitionRepository;
import com.example.demo.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Transactional
    public MemberResponseDto.getSupporterMember getSupporterMember(Long memberId, Long exhibitionId){
        Exhibition exhibition = exhibitionRepository.findById(exhibitionId).orElseThrow(()-> new IllegalArgumentException("존재하는 전시가 없습니다."));
        Member member = memberRepository.findMemberByIdAndExhibition(memberId, exhibition).orElseThrow(()-> new IllegalArgumentException("해당하는 유저가 없습니다."));
        return MemberResponseDto.getSupporterMember.builder()
                .id(member.getId())
                .loginId(member.getLoginId())
                .name(member.getName())
                .introduction(member.getIntroduction())
                .cellphone(member.getCellphone())
                .major(member.getCellphone())
                .build();
    }

    private Long id;
    private String loginId;
    private String name;
    private String introduction;
    private String cellphone;
    private String major;

    @Transactional
    public List<MemberResponseDto.getSupporterMember> getAllSupporterMember(Long exhibitionId){
        Exhibition exhibition = exhibitionRepository.findById(exhibitionId).orElseThrow(()-> new IllegalArgumentException("존재하는 전시가 없습니다."));
        List<Member> members = memberRepository.findAllByExhibitionAndMemberRole(exhibition, MemberRole.SUPPORTER);
        members.stream()
                .map( member -> {
                    MemberResponseDto.getSupporterMember.builder()
                            .id()
                        }
                )
    }
}
