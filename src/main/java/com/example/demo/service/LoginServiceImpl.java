package com.example.demo.service;

import com.example.demo.dto.authDto.SocialAuthResponse;
import com.example.demo.dto.authDto.SocialUserResponse;
import com.example.demo.type.LoginType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Component
@Qualifier("defaultLoginService")
public class LoginServiceImpl implements SocialLoginService {
    @Override
    public LoginType getServiceName() {
        return LoginType.NORMAL;
    }

    @Override
    public SocialAuthResponse getAccessToken(String authorizationCode) {
        return null;
    }

    @Override
    public SocialUserResponse getUserInfo(String accessToken) {
        return null;
    }
}

