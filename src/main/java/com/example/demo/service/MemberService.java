package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.memberDto.MemberRequestDto;
import com.example.demo.dto.memberDto.MemberResponseDto;
import com.example.demo.dto.workDto.WorkResponseDto;
import com.example.demo.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Transactional
    //한 멤버에 대한 정보 가져오기
    public ResponseEntity<MemberResponseDto.getMember> getMember(Long id){
        Member member = memberRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당하는 유저가 존재하지 않습니다."));
        MemberResponseDto.getMember getMember = MemberResponseDto.getMember.builder()
                .id(member.getId())
                .name(member.getName())
                .major(member.getMajor())
                .introduction(member.getIntroduction())
                .mainWork(member.getMainWork())
                .build();
        return ResponseEntity.ok().body(getMember);
    }

    //한 멤버가 창작한 work들 가져오기
    @Transactional
    public MemberResponseDto.getWorksByMember getWorksByMember(Long id){
        Member member = memberRepository.findById(id).orElseThrow(()->new IllegalArgumentException("존재하지 않는 유저입니다."));
        List<WorkResponseDto.getWork> getWorkList =
                member.getWorkList().stream()
                        .map(work -> {
                            return WorkResponseDto.getWork.builder()
                                    .title(work.getTitle())
                                    .imgUrl(work.getImgUrl())
                                    .contents(work.getContents())
                                    .name(work.getMember().getName())
                                    .build();
                                }
                        )
                        .collect(Collectors.toList());
        return MemberResponseDto.getWorksByMember
                .builder()
                .mainWorkTitle(member.getMainWork().getTitle())
                .mainWorkContents(member.getMainWork().getContents())
                .getWorkList(getWorkList)
                .build();
    }

    //한 멤버에 대한 정보 집어넣기
    @Transactional
    public Long registNewMemebr(MemberRequestDto.registNewMember memberRequest){
        Member member = Member.builder()
                .name(memberRequest.getName())
                .major(memberRequest.getMajor())
                .introduction(memberRequest.getIntroduction())
                .build();

        return memberRepository.save(member).getId();
    }

}
