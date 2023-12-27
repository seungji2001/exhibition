package com.example.demo.controller;

import com.example.demo.dto.RequestDto;
import com.example.demo.dto.memberDto.MemberRequestDto;
import com.example.demo.service.ParticipantService;
import com.example.demo.service.RecordService;
import com.example.demo.service.WorkService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class HomeController {

    @Autowired
    RecordService recordService;
    @Autowired
    ParticipantService memberService;
    @Autowired
    WorkService workService;

    @Value("${api.key}")
    private String getKey;

    @GetMapping(value = "/")
    public String home(Model model, RequestDto requestDto) {
        model.addAttribute("requestDto",requestDto);
        model.addAttribute("getWorksListByViewCounts",workService.getWorksListByViewCounts());
        model.addAttribute("recordList", recordService.getRecordList());
        model.addAttribute("workList", workService.getmainWorksList());
        model.addAttribute("APPKEY",getKey);

        return "home/index";
    }


    //메인페이지 들어가기
    @GetMapping(value = "/exhibition")
    public String mainPage(Model model){
//        model.addAttribute("getExhibition",exhibitionService.getExhibition(exhibition_id));
        return "/exhibition/home";
    }

    @PostMapping(value = "/record")
    public String sendRecord(@ModelAttribute("requestDto") RequestDto requestDto){
        recordService.saveRecord(requestDto);
        return "redirect:/";
    }

    //로그인
    @PostMapping(value = "/login")
    public ResponseEntity login(Model model, MemberRequestDto.loginMember loginMember, HttpSession session){
        memberService.loginMember(loginMember, session);
        return ResponseEntity.ok().build();
    }
}