package com.example.demo.controller;

import com.example.demo.dto.adminDto.AdminRequestDto;
import com.example.demo.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminController {

//    @Autowired
//    AdminService adminService;
//
//    //전시 페이지로 이동하기
//    @GetMapping(value = "/admin_registration_exhibition")
//    public String get_admin_registration_exhibition() {
//        return "admin/admin_registration_exhibition";
//    }
//
//    //전시 등록
//    @PostMapping(value = "/admin_registration_exhibition")
//    public ResponseEntity send_admin_registration_exhibition(@RequestBody AdminRequestDto.registExhibition registExhibition) {
//        return adminService.saveExhibition(registExhibition);
//    }
//
//    //등록된 전시 모두 가져오기
//    @GetMapping(value = "/get_all_exhibitions")
//    public ResponseEntity<List<AdminRequestDto.registExhibition>> getAllRegistedExhibition(){
//        return adminService.getAllRegistedExhibitions();
//    }

}
