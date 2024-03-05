package com.example.demo.service.V3;

import com.example.demo.domain.V3.MagazineUser;
import com.example.demo.dto.V3.UserDto;
import com.example.demo.repository.V3.MagazineRepository;
import com.example.demo.repository.V3.MagazineUserRepository;
import com.example.demo.repository.V3.UserRepository;
import com.example.demo.type.MUserType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final MagazineUserRepository magazineUserRepository;
    private final MagazineRepository magazineRepository;
    private final UserRepository userRepository;

    public void saveParticipant(Long userId, Long magazineId, UserDto.SaveApplication saveApplication) {
        MagazineUser magazineUser = magazineUserRepository.findByUserIdAndMagazineIdAndMUserType(userId, magazineId, MUserType.PARTICIPANT).orElseThrow();
        magazineUser.updateApplicant(saveApplication.getApplicant());
    }

    //잡지에 해당되는 모든 지원서들 보기
    public List<UserDto.getApplicant> getApplicants(Long magazineId){
        List<MagazineUser> magazineUser = magazineUserRepository.findByMagazineIdAndMUserType(magazineId, MUserType.APPLICANT);
        return magazineUser.stream()
                .map(
                        m->UserDto.getApplicant.builder()
                                .name(m.getUser().getName())
                                .applicant(m.getApplicant())
                                .build()
                )
                .collect(Collectors.toList());

    }

    //userId가 지원한 magazineId에 가진 지원서 보기
    public UserDto.getApplicant getApplicant(Long userId, Long magazineId) {
        MagazineUser magazineUser = magazineUserRepository.findByUserIdAndMagazineIdAndMUserType(userId,magazineId, MUserType.APPLICANT).orElseThrow();
        return UserDto.getApplicant
                .builder()
                .name(magazineUser.getUser().getName())
                .applicant(magazineUser.getApplicant())
                .build();

    }
}
