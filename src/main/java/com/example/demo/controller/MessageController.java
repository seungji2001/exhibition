package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.dto.messageDto.MessageRequestDto;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MessageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@RequiredArgsConstructor
@Controller
public class MessageController {

    @Autowired
    MessageService messageService;
    @PostMapping(value = "/message/{id}")
    public String sendMessage(@PathVariable Long id, MessageRequestDto.sendMessage message, HttpServletResponse response) throws IOException {
        messageService.sendMessage(id, message);
        return "redirect:/member/" + id + "#contact";
    }
}
