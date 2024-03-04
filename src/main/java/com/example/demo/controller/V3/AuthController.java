package com.example.demo.controller.V3;

import com.example.demo.service.AuthService.OAuth2Service;
import com.example.demo.type.LoginType;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final OAuth2Service oAuth2Service;


    //callback url 정보 담아오기
    @GetMapping("/{loginType}")
    public ResponseEntity<String> getRedirectUrl(@PathVariable LoginType loginType){
        return ResponseEntity.ok().body(oAuth2Service.getRedirectUrl(LoginType.GGL));
    }

    @GetMapping("/google/callback")
    public void getGoogleAccessToken(String code, HttpServletResponse response) throws IOException {
        String accessToken = oAuth2Service.getAccessToken(code, LoginType.GGL);
        oAuth2Service.login(response, accessToken, LoginType.GGL);
    }

}


