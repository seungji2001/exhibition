package com.example.demo.controller;

import com.example.demo.dto.RequestDto;
import com.example.demo.service.MemberService;
import com.example.demo.service.RecordService;
import com.example.demo.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class HomeController {

    @Autowired
    RecordService recordService;

    @Autowired
    MemberService memberService;

    @Autowired
    WorkService workService;

    @GetMapping(value = "/")
    public String home(Model model, RequestDto requestDto) {
        model.addAttribute("requestDto",requestDto);
        model.addAttribute("getWorksListByViewCounts",workService.getWorksListByViewCounts());
        model.addAttribute("recordList", recordService.getRecordList());
        model.addAttribute("workList", workService.getmainWorksList());
//        model.addAttribute("workList", memberService.getMemberList());
        return "home/index";
    }

    @PostMapping(value = "/record")
    public String sendRecord(@ModelAttribute("requestDto") RequestDto requestDto){
        recordService.saveRecord(requestDto);
        return "redirect:/";
    }
}