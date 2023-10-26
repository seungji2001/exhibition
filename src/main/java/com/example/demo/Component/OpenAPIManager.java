package com.example.demo.Component;

import com.example.demo.dto.ChatBotDto.ChatBotRequestDto;
import com.example.demo.dto.ChatBotDto.ChatBotResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class OpenAPIManager {
    Gson gson = new Gson();

    public ChatBotResponseDto.ResponseAnswer getWikiQA(String openApiURL, String accessKey, String type, ChatBotRequestDto.RequestQuestion requestQuestion) throws JsonProcessingException, UnsupportedEncodingException {

        Map<String, Object> request = new HashMap<>();
        Map<String, String> argument = new HashMap<>();

        argument.put("question", requestQuestion.getQuestion());
        argument.put("type", type);

        request.put("argument", argument);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json; charset=UTF-8");
        headers.set("Authorization", accessKey);

        HttpEntity<byte[]> entity = new HttpEntity<>(gson.toJson(request).getBytes(StandardCharsets.UTF_8), headers);

        ResponseEntity<String> response = restTemplate.exchange(openApiURL, HttpMethod.POST, entity, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response.getBody());

        JsonNode answer = jsonNode.get("return_object").findValue("AnswerInfo");

        return ChatBotResponseDto.ResponseAnswer.builder()
                .answer(answer.get(0).get("answer").asText())
                .build();
    }

    public List<String> getWiseNLU(String accessKey, ChatBotRequestDto.RequestQuestion requestQuestion) throws JsonProcessingException, UnsupportedEncodingException {

        String openApiURL = "http://aiopen.etri.re.kr:8000/WiseNLU";

        Map<String, Object> request = new HashMap<>();
        Map<String, String> argument = new HashMap<>();

        String analysisCode = "ner";        // 언어 분석 코드
        Gson gson = new Gson();

        argument.put("analysis_code", analysisCode);
        argument.put("text", requestQuestion.getQuestion());

        request.put("argument", argument);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json; charset=UTF-8");
        headers.set("Authorization", accessKey);

        HttpEntity<byte[]> entity = new HttpEntity<>(gson.toJson(request).getBytes(StandardCharsets.UTF_8), headers);

        ResponseEntity<String> response = restTemplate.exchange(openApiURL, HttpMethod.POST, entity, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response.getBody());
        List<JsonNode> datasets = StreamSupport
                .stream(jsonNode.get("return_object").get("sentence").findValue("word").spliterator(), false)
                .toList();

        List<String> words = new ArrayList<>();
        for(int i = 0; i<datasets.size(); i++){
            words.add(datasets.get(i).get("text").asText());
        }

        return words;
    }

}