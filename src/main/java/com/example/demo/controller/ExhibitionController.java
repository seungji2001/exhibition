package com.example.demo.controller;

import com.example.demo.dto.exhibitionDto.ExhibitionRequestDto;
import com.example.demo.dto.exhibitionDto.ExhibitionResponseDto;
import com.example.demo.service.ExhibitionService;
import com.example.demo.service.WorkService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ExhibitionController {

    @Autowired
    ExhibitionService exhibitionService;
    @Autowired
    WorkService workService;

    //exhibition 가져오기
    @GetMapping(value = "/exhibition/{exhibition_id}")
    public String getExhibition(Model model, @PathVariable("exhibition_id") Long exhibition_id){
        model.addAttribute("getExhibition",exhibitionService.getExhibition(exhibition_id));

        model.addAttribute("workList", workService.getAllWork(exhibition_id));

        return "/exhibition/index1";
    }

    //exhibition 등록
//    @PostMapping(value="/exhibition")
//    public ResponseEntity<Long> registrationExhibition(@Valid @RequestBody ExhibitionRequestDto.registrationExhibition registrationExhibition){
//        return ResponseEntity.ok().body(exhibitionService.registrationExhibition(registrationExhibition));
//    }

    //exhibition 모두 가져오기
//    @GetMapping(value = "/exhibitions")
//    public ResponseEntity<List<ExhibitionResponseDto.getExhibition>> getExhibitions(){
//        return ResponseEntity.ok().body(exhibitionService.getExhibitions());
//    }

    //exhibition 수정하기
//    @PutMapping(value = "/exhibition/{exhibition_id}")
//    public ResponseEntity<ExhibitionResponseDto.getExhibition> updateExhibition(@PathVariable("exhibition_id")Long exhibition_id,@RequestBody ExhibitionRequestDto.updateExhibition updateExhibition){
//         return ResponseEntity.ok().body(exhibitionService.updateExhibition(exhibition_id, updateExhibition));
//    }

    //exhibition 삭제하기
//    @DeleteMapping(value = "/exhibition/{exhibition_id}")
//    public ResponseEntity<Long> deleteExhibition(@PathVariable("exhibition_id")Long exhibition_id){
//        exhibitionService.deleteExhibition(exhibition_id);
//        return ResponseEntity.ok().body(exhibition_id);
//    }
}