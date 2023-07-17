package com.example.demo.service;

import com.example.demo.domain.Record;
import com.example.demo.dto.RequestDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.repository.RecordRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {

    @Autowired
    RecordRepository recordRepository;

    @Transactional
    public List<ResponseDto.getParticipantsRecords> getRecordLists(){
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
