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

    //멤버별 작품 등록하기
    @PostMapping(value = "/work")
    public String registrationWork (WorkRequestDto.registNewWork newWork){
        workService.registrationNewWork(newWork);
        return "redirect:/member#work";
    }
}
