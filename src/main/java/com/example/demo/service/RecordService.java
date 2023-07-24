package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.domain.Record;
import com.example.demo.dto.RequestDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.RecordRepository;
import com.example.demo.repository.WorkRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecordService {

    @Autowired
    RecordRepository recordRepository;
    @Autowired
    WorkRepository workRepository;
    @Autowired
    MemberRepository memberRepository;

    @Transactional
    public List<ResponseDto.getParticipantsRecords> getRecordList(){
        return  recordRepository.findAll()
                .stream().map(record -> {
                    return ResponseDto.getParticipantsRecords.builder()
                            .writer(record.getWriter())
                            .content(record.getContent()).build();
                })
                .toList();
    }

    @Transactional
    public void saveRecord(RequestDto requestDto){
        Record record = Record.builder()
                .writer(requestDto.getWriter())
                .content(requestDto.getContent())
                .build();
        recordRepository.save(record);

    }
}
