package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.dto.memberDto.MemberRequestDto;
import com.example.demo.dto.memberDto.MemberResponseDto;
import com.example.demo.dto.messageDto.MessageRequestDto;
import com.example.demo.dto.workDto.WorkRequestDto;
import com.example.demo.service.AdminService;
import com.example.demo.service.MemberService;
import com.example.demo.service.SupporterService;
import com.example.demo.service.WorkService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MemberController {

    @Autowired
    MemberService memberService;
    @Autowired
    WorkService workService;
    @Autowired
    AdminService adminService;
    @Autowired
    SupporterService supporterService;

    //관리자로 멤버 등록
    @PostMapping(value = "/admin/member")
    public ResponseEntity<Long> registrationAdminMember(MemberRequestDto.adminMember adminMember){
        return ResponseEntity.ok().body(adminService.registration(adminMember));
    }

    //서포터로 멤버 등록
    @PostMapping(value = "/supporter/member/{exhibition_id}")
    public ResponseEntity<Long> registrationSupporterMember(MemberRequestDto.supporterMember supporterMember, @PathVariable("exhibition_id") Long exhibitionId){
        return ResponseEntity.ok().body(supporterService.registration(supporterMember, exhibitionId));
    }

    //새로운 멤버 등록
    @PostMapping(value = "/member/{exhibition_id}")
    public ResponseEntity<Long> registrationMemebr(MemberRequestDto.participantsMember participantsMember, @PathVariable("exhibition_id") Long exhibitionId){
        return ResponseEntity.ok().body(memberService.registNewMemebr(participantsMember, exhibitionId));
    }

    //멤버 조회하였을때 -- v1
    @GetMapping(value = "/member/{id}")
    public String getMember(
            Model model,
            @PathVariable("id") Long id,
            MessageRequestDto.sendMessage sendMessage
    ){
        workService.countView(id);
        model.addAttribute("memberInformation", memberService.getMember(id).getBody());
        model.addAttribute("sendMessage", sendMessage);
        return "home/member";
    }

    //서포터를 조회하였을때
    @GetMapping(value = "/supporter/member/{member_id}/exhibition/{exhibition_id}")
    public ResponseEntity<MemberResponseDto.getSupporterMember> getSupporter(@PathVariable("member_id")Long member_id,@PathVariable("exhibition_id")Long exhibition_id){
        return ResponseEntity.ok().body(supporterService.getSupporterMember(member_id, exhibition_id));
    }

    //모든 서포터를 조회하였을때
    @GetMapping(value = "/supporter/exhibition/{exhibition_id}")
    public ResponseEntity<List<MemberResponseDto.getSupporterMember>> getAllSupporters(@PathVariable("exhibition_id")Long exhibition_id){
        return ResponseEntity.ok().body(supporterService.getAllSupporterMember(exhibition_id));
    }

    //한 멤버의 모든 작품들을 조회
    @GetMapping(value = "/member/{id}/works")
    public String getWorksByMemberId(Model model, @PathVariable("id") Long id) {
        model.addAttribute("memberWork", memberService.getWorksByMember(id));
        return "member_works :: works";
    }

    //한 멤버의 메인 작품으로 등록하기
    @PostMapping(value = "/member/{member_id}/mainWork/{work_id}")
    public ResponseEntity registrationMainWorkToMember(@PathVariable("member_id")Long member_id, @PathVariable("work_id")Long work_id){
        memberService.registrationMainWorkToMember(member_id, work_id);

        return ResponseEntity.ok().build();
    }


}
