package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Controller
public class ChatBotController {

    @Value("${wiki.api.key}")
    private String wikiKey;

}
