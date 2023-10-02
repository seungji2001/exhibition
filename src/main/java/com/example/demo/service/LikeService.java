package com.example.demo.service;

import com.example.demo.domain.LikeEntity;
import com.example.demo.domain.Member;
import com.example.demo.domain.Work;
import com.example.demo.dto.likeDto.LikeRequestDto;
import com.example.demo.event.LikeCreatedEvent;
import com.example.demo.repository.LikeRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final MemberRepository memberRepository;

    private final WorkRepository workRepository;
    private final ApplicationEventPublisher eventPublisher;

    public Long createLike(LikeRequestDto.createLike createLikeDTO) {
        Optional<Member> member = memberRepository.findById(createLikeDTO.getMemberId());
        Optional<Work> work = workRepository.findById(createLikeDTO.getWorkId());

        LikeEntity like = LikeEntity.builder()
                .memberId(member.get())
                .workId(work.get())
                .build();

        likeRepository.save(like);

        LikeCreatedEvent event = new LikeCreatedEvent(this, like);
        eventPublisher.publishEvent(event);

        return likeRepository.save(like).getLikeId();
    }

    public String deleteLike(Long member_id, Long work_id){

        Optional<Member> member = memberRepository.findById(member_id);
        Optional<Work> work = workRepository.findById(work_id);


        Optional<LikeEntity> like = likeRepository.findByMemberIdAndWorkId(member.get(), work.get());

        return "success";
    }

    public Long countLike(Long work_id){

        Optional<Work> work = workRepository.findById(work_id);

        Long like = likeRepository.countByWorkId(work.get());

        return like;

    }
}
