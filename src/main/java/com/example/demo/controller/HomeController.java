package com.example.demo.controller;

import com.example.demo.dto.RequestDto;
import com.example.demo.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class HomeController {

    @Autowired
    RecordService recordService;

    @Value("${api.key}")
    private String getKey;

    @GetMapping(value = "/")
    public String home(Model model, RequestDto requestDto) {
        model.addAttribute("requestDto",requestDto);
        model.addAttribute("recordList", recordService.getRecordList());
        model.addAttribute("workList", recordService.getWorksList());
        model.addAttribute("APPKEY",getKey);
        return "home/index";
    }

    @PostMapping(value = "/record")
    public String sendRecord(@ModelAttribute("requestDto") RequestDto requestDto){
        recordService.saveRecord(requestDto);
        return "redirect:/";
    }
}