package com.example.demo.service;

import com.example.demo.type.MemberRole;
import com.example.demo.domain.Exhibition;
import com.example.demo.domain.Member;
import com.example.demo.domain.Work;
import com.example.demo.dto.memberDto.MemberRequestDto;
import com.example.demo.dto.memberDto.MemberResponseDto;
import com.example.demo.repository.ExhibitionRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.WorkRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParticipantService {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    WorkRepository workRepository;
    @Autowired
    ExhibitionRepository exhibitionRepository;

    @Transactional
    //한 멤버에 대한 정보 가져오기
    public ResponseEntity<MemberResponseDto.getMember> getMember(Long id){
        Member member = memberRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당하는 유저가 존재하지 않습니다."));
        MemberResponseDto.getMember getMember = MemberResponseDto.getMember.builder()
                .id(member.getId())
                .name(member.getName())
                .major(member.getMajor())
                .introduction(member.getIntroduction())
                .mainWork(member.getMainWork())
                .build();
        return ResponseEntity.ok().body(getMember);
    }

    //한 멤버가 창작한 work들 가져오기
//    @Transactional --v1
//    public MemberResponseDto.getWorksByMember getWorksByMember(Long id){
//        Member member = memberRepository.findById(id).orElseThrow(()->new IllegalArgumentException("존재하지 않는 유저입니다."));
//        List<WorkResponseDto.getWork> getWorkList =
//                member.getWorkList().stream()
//                        .map(work -> {
//                            return WorkResponseDto.getWork.builder()
//                                    .title(work.getTitle())
//                                    .imgUrl(work.getImgUrl())
//                                    .contents(work.getContents())
//                                    .name(work.getMember().getName())
//                                    .build();
//                                }
//                        )
//                        .collect(Collectors.toList());
//        return MemberResponseDto.getWorksByMember
//                .builder()
//                .mainWorkTitle(member.getMainWork().getTitle())
//                .mainWorkContents(member.getMainWork().getContents())
//                .getWorkList(getWorkList)
//                .build();
//    }

    //한 멤버에 대한 정보 집어넣기
    @Transactional
    public Long registNewMemebr(MemberRequestDto.participantsMember participantsMember, Long exhibitionId){
        Exhibition exhibition = exhibitionRepository.findById(exhibitionId).orElseThrow(()-> new IllegalArgumentException("해당하는 전시가 없습니다."));
        Member member = Member.builder()
                .name(participantsMember.getName())
                .memberRole(MemberRole.PARTICIPANTS)
                .exhibition(exhibition)
                .build();

        return memberRepository.save(member).getId();
    }

    @Transactional
    public ResponseEntity registrationMainWorkToMember(Long member_id,Long work_id){
        Member member = memberRepository.findById(member_id).orElseThrow(()-> new IllegalArgumentException("해당하는 유저가 없습니다"));
        Work work = workRepository.findById(work_id).orElseThrow(()-> new IllegalArgumentException("해당하는 작품이 없습니다."));
        member.setMainWork(work);
        return ResponseEntity.ok().build();
    }

    @Transactional
    public void loginMember(MemberRequestDto.loginMember loginMember, HttpSession session){
        Member member = memberRepository.findMemberByLoginIdAndPassword(loginMember.getLoginId(), loginMember.getPassword()).orElseThrow(()-> new IllegalArgumentException("아이디, 비번을 잘못 입력하셨습니다."));
        session.setAttribute("loginMember", member);
    }

    //TODO 새로 추가한 멤버 필드에 맞춰 변경하고 정리해두기
    //참가자들 모두 가져오기(일반 멤버)
    @Transactional
    public List<MemberResponseDto.getParticipants> getAllParticipants(Long exhibition_id){
        Exhibition exhibition = exhibitionRepository.findById(exhibition_id).orElseThrow(()->new IllegalArgumentException("해당하는 전시가 없습니다."));
        List<Member> members = memberRepository.findAllByExhibitionAndMemberRole(exhibition, MemberRole.PARTICIPANTS);
        return members.stream()
                .map(
                member -> {
                    return MemberResponseDto.getParticipants.builder()
                            .id(member.getId())
                            .name(member.getName())
                            .build();
                }
                )
                .collect(Collectors.toList());

    }

    @Transactional
    public MemberResponseDto.getParticipants getParticipant(Long participant_id, Long exhibition_id){
        Exhibition exhibition = exhibitionRepository.findById(exhibition_id).orElseThrow(() -> new IllegalArgumentException("해당하는 전시가 없습니다."));
        Member member = memberRepository.findMemberByIdAndExhibition(participant_id, exhibition).orElseThrow(()-> new IllegalArgumentException("해당하는 멤버가 없습니다."));
        return MemberResponseDto.getParticipants.builder()
                .id(member.getId())
                .name(member.getName())
                .build();
    }

    @Transactional //-v2
    public MemberResponseDto.getMemberVersion2 getMemberVersion2(Long member_id, Long exhibition_id){
        Exhibition exhibition = exhibitionRepository.findById(exhibition_id).orElseThrow(() -> new IllegalArgumentException("해당하는 전시가 없습니다."));
        Member member = memberRepository.findByIdAndExhibition(member_id, exhibition).orElseThrow();

        return MemberResponseDto.getMemberVersion2
                .builder()
                .id(member.getId())
                .name(member.getName())
                .introduction(member.getIntroduction())
                .img_url(member.getImg_url())
                .build();

    }
}
