package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.dto.exhibitionDto.ExhibitionRequestDto;
import com.example.demo.dto.memberDto.MemberRequestDto;
import com.example.demo.dto.workDto.WorkRequestDto;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.ExhibitionService;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@RequiredArgsConstructor
@Controller
public class AdminController {

    @Autowired
    MemberService memberService;

    @Autowired
    ExhibitionService exhibitionService;

    @GetMapping(value = "/admin")
    public String registrationMemebr(Model model
            , ExhibitionRequestDto.registrationExhibition registrationExhibition
    ){

        return "admin/NiceAdmin/adminMember/admin-profile";
    }
}
