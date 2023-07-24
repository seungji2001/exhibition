package com.example.demo.service;

import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.memberDto.MemberResponseDto;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.WorkRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkService {

    @Autowired
    MemberRepository memberRepository;

    //모든 메인 작품들 가져오기
    @Transactional
    public List<ResponseDto.getmainWorks> getWorksList(){
        return memberRepository.findAll()
                .stream().map(member -> {
                    return ResponseDto.getmainWorks.builder()
                            .title(member.getMainWork().getTitle())
                            .imgUrl(member.getMainWork().getImgUrl())
                            .contents(member.getMainWork().getContents())
                            .member(member)
                            .build();
                })
                .collect(Collectors.toList());
    }

}
