package com.example.demo.repository;

import com.example.demo.domain.client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<client,Long> {

    Optional<client> findByLogin(String loginId);
}
