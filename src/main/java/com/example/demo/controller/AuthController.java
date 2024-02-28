package com.example.demo.controller;

import com.example.demo.dto.authDto.LoginResponse;
import com.example.demo.dto.authDto.SocialLoginRequestDto;
import com.example.demo.service.OAuth2Service;
import com.example.demo.service.UserService;
import com.example.demo.type.LoginType;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    OAuth2Service oAuth2Service;
    @Autowired
    UserService userService;


    //callback url 정보 담아오기
    @GetMapping("/{loginType}")
    public ResponseEntity<String> getRedirectUrl(@PathVariable LoginType loginType){
        return ResponseEntity.ok().body(oAuth2Service.getRedirectUrl(LoginType.GGL));
    }

//    @GetMapping("/google/callback")
//    public void getGoogleAccessToken(String code, HttpServletResponse response) throws IOException {
//        String accessToken = oAuth2Service.getAccessToken(code, ELoginProvider.GOOGLE);
//        oAuth2Service.login(response, accessToken, ELoginProvider.GOOGLE);
//    }

    /*
    SocialLoginRequestDto안에 @NotNull 유효성 검사가 있으므로 해당 dto불러올때 앞에 @Valid를 설정함으로써
    유효성 검증을 해줍니다.
     */
    @PostMapping("/social-login")
    public ResponseEntity<LoginResponse> doSocialLogin(@RequestBody @Valid SocialLoginRequestDto request) {

        return ResponseEntity.created(URI.create("/social-login"))
                .body(userService.doSocialLogin(request));
    }

}


