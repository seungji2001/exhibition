package com.example.demo.service;

import com.example.demo.Component.OpenAPIManager;
import com.example.demo.domain.Exhibition;
import com.example.demo.dto.ChatBotDto.ChatBotRequestDto;
import com.example.demo.dto.ChatBotDto.ChatBotResponseDto;
import com.example.demo.repository.ExhibitionRepository;
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

    @Autowired
    ExhibitionRepository exhibitionRepository;

    public ChatBotResponseDto.ResponseAnswer getChatBotExhibition(Long exhibition_id, ChatBotRequestDto.RequestQuestion chatBotRequestDto, String getKey) throws UnsupportedEncodingException, JsonProcessingException {
        //질문에 해당되는 답변 만들어 return 하기
        List<String> words = new ArrayList<>();
        //질문 분석 api 붙이기
        words = openAPIManager.getWiseNLU(getKey, chatBotRequestDto);

        if(words.contains("몇시")){
            return ChatBotResponseDto.ResponseAnswer.builder()
                    .answer("1시")
                    .build();
        }

        return null;
    }

    public ChatBotResponseDto.ResponseAnswer getChatBotOptionExhibition(Long exhibition_id, ChatBotRequestDto.RequestQuestion chatBotRequestDto){
        Exhibition exhibition = exhibitionRepository.findById(exhibition_id).orElseThrow();
        ChatBotResponseDto.ResponseAnswer responseAnswer = null;
        if(chatBotRequestDto.getQuestion().equals("time")){
            responseAnswer = ChatBotResponseDto.ResponseAnswer.builder()
                    .answer(exhibition.getTitle())
                    .build();
        }else if(chatBotRequestDto.getQuestion().equals("period")){
            responseAnswer = ChatBotResponseDto.ResponseAnswer.builder()
                    .answer(exhibition.getStartDate() + " ~ " + exhibition.getEndDate())
                    .build();
        }else if(chatBotRequestDto.getQuestion().equals("location")){
            responseAnswer = ChatBotResponseDto.ResponseAnswer.builder()
                    .answer(exhibition.getAddress())
                    .build();
        }
        return responseAnswer;
    }
}
