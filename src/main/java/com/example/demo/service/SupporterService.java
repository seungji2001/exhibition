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
import java.util.stream.Collectors;

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
    public MemberResponseDto.getSupporterMember getSupporterMember(Long supporter_id, Long exhibitionId){
        Exhibition exhibition = exhibitionRepository.findById(exhibitionId).orElseThrow(()-> new IllegalArgumentException("존재하는 전시가 없습니다."));
        Member member = memberRepository.findMemberByIdAndExhibition(supporter_id, exhibition).orElseThrow(()-> new IllegalArgumentException("해당하는 유저가 없습니다."));
        return MemberResponseDto.getSupporterMember.builder()
                .id(member.getId())
                .loginId(member.getLoginId())
                .name(member.getName())
                .introduction(member.getIntroduction())
                .cellphone(member.getCellphone())
                .major(member.getCellphone())
                .build();
    }

    //모든 서포터들 리턴
    @Transactional
    public List<MemberResponseDto.getSupporterMember> getAllSupporterMember(Long exhibitionId) {
        Exhibition exhibition = exhibitionRepository.findById(exhibitionId).orElseThrow(() -> new IllegalArgumentException("존재하는 전시가 없습니다."));
        List<Member> members = memberRepository.findAllByExhibitionAndMemberRole(exhibition, MemberRole.SUPPORTER);
        return members.stream()
                .map(member -> {
                    return MemberResponseDto.getSupporterMember.builder()
                            .id(member.getId())
                            .loginId(member.getLoginId())
                            .name(member.getName())
                            .introduction(member.getIntroduction())
                            .cellphone(member.getCellphone())
                            .major(member.getMajor())
                            .build();
                        }
                )
                .collect(Collectors.toList());
    }

    @Transactional
    public List<MemberResponseDto.getMembersVersion2> getAllMembers(Long exhibitionId) {
        Exhibition exhibition = exhibitionRepository.findById(exhibitionId).orElseThrow(() -> new IllegalArgumentException("존재하는 전시가 없습니다."));
        List<Member> members = memberRepository.findAllByExhibitionAndMemberRole(exhibition, MemberRole.SUPPORTER);
        return members.stream()
                .map(member -> {
                    return MemberResponseDto.getMembersVersion2
                            .builder()
                            .id(member.getId())
                            .name(member.getName())
                            .img_url(member.getImg_url())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
