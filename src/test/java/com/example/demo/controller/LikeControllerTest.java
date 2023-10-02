package com.example.demo.controller;

import com.example.demo.domain.LikeEntity;
import com.example.demo.dto.likeDto.CreateLikeDTO;
import com.example.demo.dto.likeDto.LikeRequestDto;
import com.example.demo.repository.LikeRepository;
import com.example.demo.service.LikeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class LikeControllerTest {
    @Autowired
    LikeService likeService;
    @Autowired
    LikeRepository likeRepository;


    @Test
    public void 생성(){
    }
}