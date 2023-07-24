package com.example.demo.controller;

import com.example.demo.dto.memberDto.MemberResponseDto;
import com.example.demo.dto.messageDto.MessageRequestDto;
import com.example.demo.service.MemberService;
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

    @GetMapping(value = "/test")
    public String test() {

        return "home/member";
    }

    @GetMapping(value = "/member/{id}")
    public String getMember(Model model, @PathVariable("id") Long id, MessageRequestDto.sendMessage sendMessage){
        model.addAttribute("memberInformation", memberService.getMember(id).getBody());
        model.addAttribute("sendMessage", sendMessage);
        return "home/member";
    }

    @GetMapping(value = "/member/{id}/works")
    public String getWorksByMemberId(Model model, @PathVariable("id") Long id){
        model.addAttribute("memberWork", memberService.getWorksByMember(id));
        return "member_works :: works";
    }
}
