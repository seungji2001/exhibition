package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.domain.Work;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.memberDto.MemberResponseDto;
import com.example.demo.dto.workDto.WorkRequestDto;
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

    //새로운 작품 등록하기
    @Transactional
    public void registrationNewWork(WorkRequestDto.registNewWork newWork){
        //이름으로 멤버 찾기
        Member member = memberRepository.findMemberByName(newWork.getName()).orElseThrow();
        Work work = Work.builder()
                .title(newWork.getTitle())
                .contents(newWork.getContents())
                .member(member)
                .view(0l)
                .build();
        workRepository.save(work); //작업 저장

        //만약 멤버의 메인 작품이 없다면 등록한 작품을 메인 작품으로 넣기
        if(member.getMainWork() != null){
            member.setMainWork(work);
        }
    }

    //특정 작품을 메인작품으로 넣기

    //모든 메인 작품들 가져오기
    @Transactional
    public List<ResponseDto.getmainWorks> getmainWorksList(){
        return memberRepository.findAll()
                .stream()
                .filter(member -> member.getMainWork() != null)
                .map(member -> {
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
                        .filter(member -> member.getMainWork()!=null)
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
