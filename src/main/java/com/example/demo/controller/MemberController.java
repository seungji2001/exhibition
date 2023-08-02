package com.example.demo.controller;

import com.example.demo.dto.memberDto.MemberRequestDto;
import com.example.demo.dto.memberDto.MemberResponseDto;
import com.example.demo.dto.messageDto.MessageRequestDto;
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

    //세로운 멤버 등록
    @PostMapping(value = "/member")
    public String registrationMemebr(MemberRequestDto.registNewMember memberRequest){
        ResponseEntity.ok().body(memberService.registNewMemebr(memberRequest));
        return "redirect:/member";
    }

    //한 멤버의 모든 작품들을 조회
    @GetMapping(value = "/member/{id}/works")
    public String getWorksByMemberId(Model model, @PathVariable("id") Long id) {
        model.addAttribute("memberWork", memberService.getWorksByMember(id));
        return "member_works :: works";
    }
}
