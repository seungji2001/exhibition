package com.example.demo.controller;

import com.example.demo.dto.memberDto.MemberRequestDto;
import com.example.demo.dto.workDto.WorkRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class AdminController {
    //세로운 멤버 등록페이지로 넘어가기
    @GetMapping(value = "/member")
    public String registrationMemebr(Model model
            , MemberRequestDto.registNewMember registNewMember
            , WorkRequestDto.registNewWork registNewWork){
        model.addAttribute("registMember", registNewMember);
        model.addAttribute("registWork", registNewWork);
        return "home/admin";
    }
}
