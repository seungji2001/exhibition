package com.example.demo.controller;

import com.example.demo.Component.OpenAPIManager;
import com.example.demo.dto.ChatBotDto.ChatBotRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
public class ChatBotController {

    @Autowired
    OpenAPIManager openAPIManager;

    @Value("${wiki.api.key}")
    private String wikiKey;

    @PostMapping(value = "/chatbot")
    public void getWikiQA(@RequestBody ChatBotRequestDto.RequestQuestion chatBotRequestDto) throws UnsupportedEncodingException, JsonProcessingException {
        openAPIManager.getWikiQA("http://aiopen.etri.re.kr:8000/WikiQA", wikiKey,"irqa",chatBotRequestDto);
    }
}
