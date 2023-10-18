package com.example.demo.service;

import com.example.demo.domain.Exhibition;
import com.example.demo.dto.exhibitionDto.ExhibitionRequestDto;
import com.example.demo.dto.exhibitionDto.ExhibitionResponseDto;
import com.example.demo.repository.ExhibitionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExhibitionService{
    @Autowired
    ExhibitionRepository exhibitionRepository;

    //exhibition 가져오기
    @Transactional
    public ExhibitionResponseDto.getExhibition getExhibition(Long exhibition_id){
        Exhibition exhibition = exhibitionRepository.findById(exhibition_id).orElseThrow(()->new IllegalArgumentException("해당하는 전시가 존재하지 않습니다"));
        exhibition.updateView();
        return ExhibitionResponseDto.getExhibition.builder()
                .id(exhibition.getId())
                .title(exhibition.getTitle())
                .introduction(exhibition.getIntroduction())
                .location_x(exhibition.getLocation_x())
                .location_y(exhibition.getLocation_y())
                .startDate(exhibition.getStartDate())
                .endDate(exhibition.getEndDate())
                .main_poster(exhibition.getMain_poster())
                .viewCounts(exhibition.getViewCounts())
                .address(exhibition.getAddress())
                .build();
    }

    @Transactional
    public Long registrationExhibition(ExhibitionRequestDto.registrationExhibition registrationExhibition){
        Exhibition exhibition = Exhibition.builder()
                .title(registrationExhibition.getTitle())
                .introduction(registrationExhibition.getIntroduction())
                .location_x(registrationExhibition.getLocation_x())
                .location_y(registrationExhibition.getLocation_y())
                .address(registrationExhibition.getAddress())
                .startDate(registrationExhibition.getStartDate())
                .endDate(registrationExhibition.getEndDate())
                .main_poster(registrationExhibition.getMain_poster())
                .build();
        return exhibitionRepository.save(exhibition).getId();
    }

    @Transactional
    public List<ExhibitionResponseDto.getExhibition> getExhibitions(){

        return new ArrayList<>(
                exhibitionRepository.findAll().stream()
                .map(exhibition -> {
                    return ExhibitionResponseDto.getExhibition.builder()
                            .id(exhibition.getId())
                            .title(exhibition.getTitle())
                            .introduction(exhibition.getIntroduction())
                            .location_x(exhibition.getLocation_x())
                            .location_y(exhibition.getLocation_y())
                            .address(exhibition.getAddress())
                            .startDate(exhibition.getStartDate())
                            .endDate(exhibition.getEndDate())
                            .main_poster(exhibition.getMain_poster())
                            .viewCounts(exhibition.getViewCounts())
                            .build();
                })
                .toList());
    }

    @Transactional
    public Long updateExhibition(Long exhibition_id, ExhibitionRequestDto.updateExhibition updateExhibition){
        Exhibition exhibition = exhibitionRepository.findById(exhibition_id).orElseThrow(()-> new IllegalArgumentException("해당하는 전시가 존재하지 않습니다."));
        Exhibition updatedExhibition = exhibition.updateExhibition(updateExhibition);
        //변경된 값이 기존의 값과 다를경우 해당 값으로 업데이트 치기
        return exhibitionRepository.save(updatedExhibition).getId();
    }

    @Transactional
    public void deleteExhibition(Long exhibition_id){
        Exhibition exhibition = exhibitionRepository.findById(exhibition_id).orElseThrow(()-> new IllegalArgumentException("해당하는 전시가 존재하지 않습니다."));
        exhibitionRepository.delete(exhibition);
    }
}