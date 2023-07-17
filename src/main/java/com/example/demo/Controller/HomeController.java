package com.example.demo.Controller;

import com.example.demo.domain.Record;
import com.example.demo.dto.RequestDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.repository.RecordRepository;
import com.example.demo.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class HomeController {

    @Autowired
    RecordService recordService;

    @GetMapping(value = "/")
    public String home(Model model, RequestDto requestDto) {
        model.addAttribute("requestDto",requestDto);
        model.addAttribute("responseDto", recordService.getRecordLists());
        return "home/index";
    }

    @PostMapping(value = "/record")
    public String sendRecord(@ModelAttribute("requestDto") RequestDto requestDto){
        recordService.saveRecord(requestDto);
        return "redirect:/";
    }
}