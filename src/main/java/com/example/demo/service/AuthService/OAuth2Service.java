package com.example.demo.service.AuthService;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.type.LoginType;
import com.example.demo.util.OAuth2Util;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OAuth2Service {

    @Autowired
    OAuth2Util oAuth2Util;
    @Autowired
    UserRepository userRepository;

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

    public void login(HttpServletResponse response,String accessToken, LoginType loginType){
        String tempId = null;
        //만약 로그인 할 경우 로그인 타입이 구글인 경우
        if(loginType == LoginType.GGL){
            tempId = oAuth2Util.getGoogleUserInfo(accessToken);
        }

        if(tempId == null){
            throw new IllegalArgumentException("존재하지 않는 사용자 입니다.");
        }

        final String socialId = tempId;
        final String password = UUID.randomUUID().toString();

        //받아온 tempId를 tb_user table에 업데이트 한다
        User user = userRepository.findByLoginAndProvider(tempId, loginType).orElseGet(
                ()->userRepository.save(
                        User.builder()
                                .login(socialId)
                                .password(password)
                                .provider(loginType)
                                .build()
                )
        );
    }
}
