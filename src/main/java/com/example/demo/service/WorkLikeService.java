package com.example.demo.service;

import com.example.demo.domain.WorkLike;
import com.example.demo.domain.Member;
import com.example.demo.domain.Work;
import com.example.demo.dto.likeDto.CreateLikeDTO;
import com.example.demo.dto.likeDto.LikeRequestDto;
import com.example.demo.event.LikeCreatedEvent;
import com.example.demo.repository.WorkLikeRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.WorkRepository;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class WorkLikeService {

    //autowired로 자동 주입 해주세요
    @Autowired
    WorkLikeRepository workLikeRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    WorkRepository workRepository;
    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Transactional
    public Long createLike(LikeRequestDto.createLike createLike) {
        //null의 경우에만 workLike 객체 생성 가능하도록, member나 work가 없는 경우 예외처리 바로 해주세요
        Member member = memberRepository.findById(createLike.getMemberId()).orElseThrow(() -> new IllegalArgumentException("해당하는 유저가 없습니다."));
        Work work = workRepository.findById(createLike.getWorkId()).orElseThrow(() -> new IllegalArgumentException("해당하는 작품이 없습니다."));

        WorkLike like = WorkLike.builder()
                .memberId(member)
                .workId(work)
                .build();

        work.updateLikeCount();

        //new 생성자 되도록 쓰지 말아주세요 -> builder 패턴 지향
        LikeCreatedEvent event = new LikeCreatedEvent(this, like);
        eventPublisher.publishEvent(event);

        return workLikeRepository.save(like).getId();
    }

    @Transactional
    public String deleteLike(Long member_id, Long work_id){
        Member member = memberRepository.findById(member_id).orElseThrow(() -> new IllegalArgumentException("해당하는 유저가 없습니다."));
        Work work = workRepository.findById(work_id).orElseThrow(() -> new IllegalArgumentException("해당하는 작품이 없습니다."));

        WorkLike like = workLikeRepository.findByMemberIdAndWorkId(member.getId(), work.getId()).orElseThrow(() -> new IllegalArgumentException("해당하는 좋아요가 없습니다."));

        workLikeRepository.deleteById(like.getId());
        work.deleteLikeCount();

        return "success";
    }

    @Transactional
    public Long countLike(Long work_id){

        Work work = workRepository.findById(work_id).orElseThrow(() -> new IllegalArgumentException("해당하는 작품이 없습니다."));
        Long like = workLikeRepository.countByWorkId(work.getId());
        if(work.getLikeCount() == like){
            return like;
        }
        else{
            throw new IllegalArgumentException("");
        }
    }
}
