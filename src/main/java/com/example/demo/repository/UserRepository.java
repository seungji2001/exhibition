package com.example.demo.repository;

import com.example.demo.domain.User;
import com.example.demo.type.LoginType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByLogin(String loginId);

    Optional<User> findByLoginAndProvider(String loginId, LoginType loginType);
}
