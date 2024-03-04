package com.example.demo.service.AuthService;

import com.example.demo.type.LoginType;
import com.example.demo.util.OAuth2Util;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class OAuth2Service {

    @Autowired
    OAuth2Util oAuth2Util;

    public String getRedirectUrl(LoginType loginType){
        if(loginType == LoginType.GGL){
            return oAuth2Util.getGoogleRedirectUrl();
        }
        return null;
    }

    public String getAccessToken(String code, LoginType loginType){
        String accessToken = null;
        if(loginType == LoginType.GGL){
            //token 가지고 오기
            accessToken = oAuth2Util.getGoogleAccessToken(code);
        }
        return accessToken;
    }
}
