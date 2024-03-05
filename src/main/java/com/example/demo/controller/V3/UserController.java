package com.example.demo.controller.V3;

import com.example.demo.dto.V3.UserDto;
import com.example.demo.service.V3.MagazineService;
import com.example.demo.service.V3.UserService;
import com.example.demo.type.MUserType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final MagazineService magazineService;

    //작가가 magazine의 참여자로 지원서 작성
    @PostMapping("/participant/{userId}")
    public void saveParticipant(@PathVariable Long userId, Long magazineId, @RequestBody UserDto.SaveApplication saveApplication){
        magazineService.saveRole(userId,magazineId, MUserType.APPLICANT);
        userService.saveParticipant(userId, magazineId, saveApplication);
    }

    //participants요청 보기
    @GetMapping("/applicants/{magazineId}")
    public ResponseEntity<List<UserDto.getApplicant>> getApplicants(@PathVariable Long magazineId){
        return ResponseEntity.ok().body(userService.getApplicants(magazineId));
    }

    @GetMapping("/applicant/{userId}")
    public ResponseEntity<UserDto.getApplicant> getApplicant(@PathVariable Long userId, Long magazineId){
        return ResponseEntity.ok().body(userService.getApplicant(userId, magazineId));
    }
    //기획자가 해당 작가 application보고 승낙
    @PutMapping("/participants/{userId}")
    public void acceptApplication(@PathVariable Long userId, Long magazineId){
        magazineService.saveRole(userId,magazineId,MUserType.PARTICIPANT);
    }
}
