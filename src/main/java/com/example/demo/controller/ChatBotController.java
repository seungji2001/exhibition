package com.example.demo.controller;

import com.example.demo.Component.OpenAPIManager;
import com.example.demo.dto.ChatBotDto.ChatBotRequestDto;
import com.example.demo.service.ChatBotService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@Controller
public class ChatBotController {

    @Autowired
    OpenAPIManager openAPIManager;
    @Autowired
    ChatBotService chatBotService;

    @Value("${wiki.api.key}")
    private String getKey;

    @PostMapping(value = "/chatbot/wiki")
    public String getWikiQA(@RequestBody ChatBotRequestDto.RequestQuestion chatBotRequestDto, Model model) throws UnsupportedEncodingException, JsonProcessingException {
        model.addAttribute("answer", openAPIManager.getWikiQA("http://aiopen.etri.re.kr:8000/WikiQA", getKey,"irqa",chatBotRequestDto));
        return "exhibition/question::wiki";
    }

    @GetMapping(value = "/index3")
    public String index3(){
        return "exhibition/index3";
    }

//    @PostMapping(value = "/chatbot/exhibition/{exhibition_id}")
//    public String getChatBotExhibition(@RequestBody ChatBotRequestDto.RequestQuestion chatBotRequestDto, @PathVariable("exhibition_id") Long exhibition_id, Model model) throws UnsupportedEncodingException, JsonProcessingException {
//        model.addAttribute("answer", chatBotService.getChatBotExhibition(exhibition_id, chatBotRequestDto, getKey));
//        return "exhibition/question::wiki";
//    }

    @PostMapping(value = "/chatbot/exhibition/option/{exhibition_id}")
    public String getChatBotOptionExhibition(@RequestBody ChatBotRequestDto.RequestQuestion chatBotRequestDto, @PathVariable("exhibition_id") Long exhibition_id, Model model) throws UnsupportedEncodingException, JsonProcessingException {
        model.addAttribute("answer", chatBotService.getChatBotOptionExhibition(exhibition_id, chatBotRequestDto));
        return "exhibition/question::wiki";
    }

//    @GetMapping(value = "/question")
//    public String question(){
//        return "exhibition/question::wiki";
//    }

//    @PostMapping(value = "/chatbot/exhibition")
//    public ResponseEntity<ChatBotResponseDto.ResponseAnswer> getExhibitionAnswer(@RequestBody ChatBotRequestDto.RequestQuestion chatBotRequestDto){
//        return ResponseEntity.ok().body(chatBotService.getAnswer(chatBotRequestDto));
//    }
}
