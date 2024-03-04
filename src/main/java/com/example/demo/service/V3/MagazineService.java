package com.example.demo.service.V3;

import com.example.demo.domain.V3.Magazine;
import com.example.demo.domain.V3.MagazineUser;
import com.example.demo.domain.V3.User;
import com.example.demo.dto.V3.MagazineDto;
import com.example.demo.repository.V3.MagazineRepository;
import com.example.demo.repository.V3.MagazineUserRepository;
import com.example.demo.repository.V3.UserRepository;
import com.example.demo.type.MUserType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MagazineService {
    private final UserRepository userRepository;
    private final MagazineUserRepository magazineUserRepository;
    private final MagazineRepository magazineRepository;

    @Transactional
    public Long save(MagazineDto.SaveMagazineDto saveMagazineDto) {
        Magazine magazine = Magazine.builder()
                .title(saveMagazineDto.getTitle())
                .mainPostUrl(saveMagazineDto.getMainPostUrl())
                .introduction(saveMagazineDto.getIntroduction())
                .plannerName(saveMagazineDto.getPlannerName())
                .plannerIntroduction(saveMagazineDto.getIntroduction())
                .isPublish(false)
                .build();
        return magazineRepository.save(magazine).getId();
    }

    @Transactional
    public void saveRole(Long userId, Long magazineId, MUserType mUserType) {
        User user = userRepository.findById(userId).orElseThrow();
        Magazine magazine = magazineRepository.findById(magazineId).orElseThrow();
        MagazineUser magazineUser = MagazineUser.builder()
                .user(user)
                .magazine(magazine)
                .mUserType(mUserType)
                .build();
        magazineUserRepository.save(magazineUser);
    }

    //아직 발매 안된 magazine
    @Transactional
    public List<MagazineDto.getMagazineDto> getMagazines(Boolean isPublish) {
        List<Magazine> magazines = magazineRepository.findAllByIsPublish(isPublish);
        return magazines.stream()
                .map(m -> MagazineDto.getMagazineDto.builder()
                        .title(m.getTitle())
                        .mainPostUrl(m.getMainPostUrl())
                        .introduction(m.getIntroduction())
                        .plannerName(m.getPlannerName())
                        .plannerIntroduction(m.getPlannerIntroduction())
                        .build()
                )
                .toList();
    }

    @Transactional
    public MagazineDto.getMagazineDto getMagazine(Long magazineId) {
        Magazine magazine = magazineRepository.findById(magazineId).orElseThrow();
        return MagazineDto.getMagazineDto.builder()
                .title(magazine.getTitle())
                .mainPostUrl(magazine.getMainPostUrl())
                .introduction(magazine.getIntroduction())
                .plannerName(magazine.getPlannerName())
                .plannerIntroduction(magazine.getPlannerIntroduction())
                .build();
    }

    @Transactional
    public Long publish(Long magazineId) {
        Magazine magazine = magazineRepository.findById(magazineId).orElseThrow();
        magazine.publish();
        return magazineId;
    }
}
