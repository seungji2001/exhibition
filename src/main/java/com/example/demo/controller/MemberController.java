package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.dto.memberDto.MemberRequestDto;
import com.example.demo.dto.memberDto.MemberResponseDto;
import com.example.demo.dto.messageDto.MessageRequestDto;
import com.example.demo.dto.workDto.WorkRequestDto;
import com.example.demo.service.MemberService;
import com.example.demo.service.WorkService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class MemberController {

    @Autowired
    MemberService memberService;
    @Autowired
    WorkService workService;

    @GetMapping(value = "/test")
    public String test() {

        return "home/member";
    }

    //세로운 멤버 등록페이지로 넘어가기
    @GetMapping(value = "/member")
    public String registrationMemebr(Model model
            , MemberRequestDto.registNewMember registNewMember
            , WorkRequestDto.registNewWork registNewWork){
        model.addAttribute("registMember", registNewMember);
        model.addAttribute("registWork", registNewWork);
        return "home/admin";
    }

    //세로운 멤버 등록
    @PostMapping(value = "/member")
    public String registrationMemebr(MemberRequestDto.registNewMember memberRequest){
        ResponseEntity.ok().body(memberService.registNewMemebr(memberRequest));
        return "redirect:/member";
    }

    //멤버를 조회 하였을때
    @GetMapping(value = "/member/{id}")
    public String getMember(
            Model model,
            @PathVariable("id") Long id,
            MessageRequestDto.sendMessage sendMessage
    ){
        workService.countView(id);
        model.addAttribute("memberInformation", memberService.getMember(id).getBody());
        model.addAttribute("sendMessage", sendMessage);
        return "home/member";
    }

    //한 멤버의 모든 작품들을 조회
    @GetMapping(value = "/member/{id}/works")
    public String getWorksByMemberId(Model model, @PathVariable("id") Long id) {
        model.addAttribute("memberWork", memberService.getWorksByMember(id));
        return "member_works :: works";
    }

    //한 멤버의 메인 작품으로 등록하기
    @PostMapping(value = "/member/{member_id}/mainWork/{work_id}")
    public ResponseEntity registrationMainWorkToMember(@PathVariable("member_id")Long member_id, @PathVariable("work_id")Long work_id){
        memberService.registrationMainWorkToMember(member_id, work_id);

        return ResponseEntity.ok().build();
    }


}
