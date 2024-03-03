package com.example.demo.service.FeignAuthService;

import com.example.demo.dto.authDto.SocialAuthResponse;
import com.example.demo.dto.authDto.SocialUserResponse;
import com.example.demo.type.LoginType;
import org.springframework.stereotype.Service;

@Service
public interface SocialLoginService {
    LoginType getServiceName();
    SocialAuthResponse getAccessToken(String authorizationCode);
    SocialUserResponse getUserInfo(String accessToken);
}
