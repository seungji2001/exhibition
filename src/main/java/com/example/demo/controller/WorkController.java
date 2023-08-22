package com.example.demo.controller;

import com.example.demo.dto.memberDto.MemberRequestDto;
import com.example.demo.dto.workDto.WorkRequestDto;
import com.example.demo.dto.workDto.WorkResponseDto;
import com.example.demo.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class WorkController {

    @Autowired
    WorkService workService;

    //멤버별 작품 등록하기 -v1
    @PostMapping(value = "/work/version1")
    public String registrationWork (WorkRequestDto.registNewWork newWork){
        workService.registrationNewWork(newWork);
        return "redirect:/member#work";
    }

    //서포터 작품 등록하기, 만약 작품이 하나도 없는 경우 메인 작품으로
    @PostMapping(value = "/exhibition/{exhibition_id}/participants/{participants_id}/work")
    public ResponseEntity<Long> registrationWork (WorkRequestDto.registSupporterWork work, @PathVariable("exhibition_id")Long exhibition_id, @PathVariable("participants_id")Long participants_id){
        return ResponseEntity.ok().body(workService.registrationWorkToMember(work, exhibition_id, participants_id));
    }

    //서포터 메인 작품 변경하기

    //서포터 작품 삭제하기

    //서포터 작품 수정하기

    //서포터 작품 클릭시 조회수와 누가 클릭했는지 기록남기기(만약 참가자로 등록이 되어있다면, 참가자 이름도,남기기)

    //서포터 작품 좋아요 수 추가하기

    //서포터 작품에 대한 감상평 보기

}
