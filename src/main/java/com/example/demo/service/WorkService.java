package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.domain.Work;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.memberDto.MemberResponseDto;
import com.example.demo.dto.workDto.WorkResponseDto;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.WorkRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkService {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    WorkRepository workRepository;

    //모든 메인 작품들 가져오기
    @Transactional
    public List<ResponseDto.getmainWorks> getmainWorksList(){
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

    @Transactional
    public void countView(Long member_id){
        Work work = memberRepository.findById(member_id).get().getMainWork();
        work.updateView();
    }

    @Transactional
    public List<WorkResponseDto.getWorkThumbnail> getWorksListByViewCounts(){
        //view count 가 높은 순으로 나열하기 -> default로 메인 작품은 모두 보여주기
        List<WorkResponseDto.getWorkThumbnail> getWorkList =
                memberRepository.findAll()
                .stream()
                .map(member -> {
                    return WorkResponseDto.getWorkThumbnail.builder()
                            .name(member.getMainWork().getMember().getName())
                            .title(member.getMainWork().getTitle())
                            .imgUrl(member.getMainWork().getImgUrl())
                            .contents(member.getMainWork().getContents())
                            .viewCount(member.getMainWork().getView())
                            .build();
                })
                .collect(Collectors.toList());
        //main work가 아닌 다른 work들 중 조회수가 한개 이상인 경우

        getWorkList.addAll(
                workRepository.findAll()
                .stream()
                .filter(work -> work.getView() >= 1l)
                .map(work -> WorkResponseDto.getWorkThumbnail.builder()
                        .title(work.getTitle())
                        .name(work.getMember().getName())
                        .imgUrl(work.getImgUrl())
                        .contents(work.getContents())
                        .viewCount(work.getView())
                        .build()
                )
                .filter(work -> !getWorkList.contains(work))
                .collect(Collectors.toList())
        );

        //getWorkList를 조회수 순서대로 정렬
        return getWorkList.stream()
                .sorted(Comparator.comparing(WorkResponseDto.getWorkThumbnail::getViewCount).reversed())
                .collect(Collectors.toList());
    }
}
