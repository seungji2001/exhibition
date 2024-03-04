package com.example.demo.repository.V3;

import com.example.demo.domain.V3.MagazineUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagazineUserRepository extends JpaRepository<MagazineUser, Long> {
}
