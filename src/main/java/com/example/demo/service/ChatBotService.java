package com.example.demo.service;

import com.example.demo.Component.OpenAPIManager;
import com.example.demo.dto.ChatBotDto.ChatBotRequestDto;
import com.example.demo.dto.ChatBotDto.ChatBotResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChatBotService {

    @Autowired
    OpenAPIManager openAPIManager;

    @Transactional
    public void getChatBotExhibition(Long exhibition_id, ChatBotRequestDto.RequestQuestion chatBotRequestDto, String getKey) throws UnsupportedEncodingException, JsonProcessingException {
        //질문에 해당되는 답변 만들어 return 하기
        List<String> words = new ArrayList<>();
        //질문 분석 api 붙이기
        words = openAPIManager.getWiseNLU(getKey, chatBotRequestDto);
    }
}
