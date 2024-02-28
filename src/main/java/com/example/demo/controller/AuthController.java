package com.example.demo.controller;

import com.example.demo.service.OAuth2Service;
import com.example.demo.type.LoginType;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    OAuth2Service oAuth2Service;

    //callback url 정보 담아오기
    @GetMapping("/{loginType}")
    public ResponseEntity<String> getRedirectUrl(@PathVariable LoginType loginType){
        return ResponseEntity.ok().body(oAuth2Service.getRedirectUrl(LoginType.GGL));
    }

}
