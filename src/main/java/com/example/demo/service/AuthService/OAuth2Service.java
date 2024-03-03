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
}
