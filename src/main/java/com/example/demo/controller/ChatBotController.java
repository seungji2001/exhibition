package com.example.demo.controller;

import com.example.demo.Component.OpenAPIManager;
import com.example.demo.dto.ChatBotDto.ChatBotRequestDto;
import com.example.demo.dto.ChatBotDto.ChatBotResponseDto;
import com.example.demo.service.ChatBotService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@Controller
public class ChatBotController {

    @Autowired
    OpenAPIManager openAPIManager;
    @Autowired
    ChatBotService chatBotService;

    @Value("${wiki.api.key}")
    private String wikiKey;

    @PostMapping(value = "/chatbot/wiki")
    public ResponseEntity<ChatBotResponseDto.ResponseAnswer> getWikiQA(@RequestBody ChatBotRequestDto.RequestQuestion chatBotRequestDto) throws UnsupportedEncodingException, JsonProcessingException {
        return ResponseEntity.ok().body(openAPIManager.getWikiQA("http://aiopen.etri.re.kr:8000/WikiQA", wikiKey,"irqa",chatBotRequestDto));
    }

    @GetMapping(value = "/index3")
    public String index3(){
        return "exhibition/index3";
    }

    @GetMapping(value = "/question")
    public String question(){
        return "exhibition/question::wiki";
    }

//    @PostMapping(value = "/chatbot/exhibition")
//    public ResponseEntity<ChatBotResponseDto.ResponseAnswer> getExhibitionAnswer(@RequestBody ChatBotRequestDto.RequestQuestion chatBotRequestDto){
//        return ResponseEntity.ok().body(chatBotService.getAnswer(chatBotRequestDto));
//    }
}
