package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.domain.Message;
import com.example.demo.dto.messageDto.MessageRequestDto;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MessageRepository;
import jakarta.transaction.Transactional;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MessageRepository messageRepository;

    @Transactional
    public ResponseEntity sendMessage(Long id, MessageRequestDto.sendMessage sendMessage){
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당하는 유저가 없습니다."));
        Message message = Message.builder()
                .title(sendMessage.getTitle())
                .contents(sendMessage.getContents())
                .email(sendMessage.getEmail())
                .sender(sendMessage.getSender())
                .receiver(member)
                .build();
        Long messageId = messageRepository.save(message).getId();
        return ResponseEntity.ok().body(messageId);
    }
}
