package com.example.demo.repository.V3;

import com.example.demo.domain.V3.MagazineUser;
import com.example.demo.type.MUserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MagazineUserRepository extends JpaRepository<MagazineUser, Long> {
    Optional<MagazineUser> findByUserIdAndMagazineIdAndMUserType(Long userId, Long magazineId, MUserType userType);

    List<MagazineUser> findByMagazineIdAndMUserType(Long magazineId, MUserType mUserType);
}
