package com.example.demo.service;

import com.example.demo.domain.Exhibition;
import com.example.demo.domain.Member;
import com.example.demo.dto.adminDto.AdminRequestDto;
import com.example.demo.dto.memberDto.MemberResponseDto;
import com.example.demo.repository.ExhibitionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    ExhibitionRepository exhibitionRepository;

    @Transactional
    //전시 등록하기
    public ResponseEntity saveExhibition(AdminRequestDto.registExhibition registExhibition){
        Exhibition exhibition = Exhibition.builder()
                .title(registExhibition.getTitle())
                .introduction(registExhibition.getIntroduction())
                .location_x(registExhibition.getLocation_x())
                .location_y(registExhibition.getLocation_y())
                .startDate(registExhibition.getStartDate())
                .endDate(registExhibition.getEndDate())
                .build();
        return ResponseEntity.ok().body(exhibitionRepository.save(exhibition).getId());
    }

    @Transactional
    //전시 등록하기
    public ResponseEntity<List<AdminRequestDto.registExhibition>> getAllRegistedExhibitions(){
        List<AdminRequestDto.registExhibition> registExhibition = exhibitionRepository.findAll()
                .stream()
                .map(exhibition -> {
                    return AdminRequestDto.registExhibition.builder()
                            .exhibitionId(exhibition.getId())
                            .title(exhibition.getTitle())
                            .introduction(exhibition.getIntroduction())
                            .location_x(exhibition.getLocation_x())
                            .location_y(exhibition.getLocation_y())
                            .startDate(exhibition.getStartDate())
                            .endDate(exhibition.getEndDate())
                            .build();
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(registExhibition);
    }
}
