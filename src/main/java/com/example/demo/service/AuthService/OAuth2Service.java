package com.example.demo.service.AuthService;

import com.example.demo.constant.Constants;
import com.example.demo.domain.User;
import com.example.demo.dto.jwtDto.JwtTokenDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.jwt.JwtProvider;
import com.example.demo.type.EUserType;
import com.example.demo.type.LoginType;
import com.example.demo.util.CookieUtil;
import com.example.demo.util.JwtUtil;
import com.example.demo.util.OAuth2Util;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OAuth2Service {

    @Autowired
    OAuth2Util oAuth2Util;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    JwtUtil jwtUtil;

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

    public void login(HttpServletResponse response,String accessToken, LoginType loginType) throws IOException {
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

        //JwtToken만들기
        JwtTokenDto jwtToken = jwtProvider.createTokens(user.getLogin());
        user.updateRefreshToken(jwtToken.refreshToken());

        sendAccessTokenAndRefreshToken(user, response, jwtToken.accessToken(), jwtToken.refreshToken());
    }

    public void sendAccessTokenAndRefreshToken(User user, HttpServletResponse response, String accessToken, String refreshToken) throws IOException, IOException {
        response.setStatus(HttpServletResponse.SC_OK);

        CookieUtil.addCookie(response, Constants.AUTHORIZATION, accessToken);
        CookieUtil.addSecureCookie(response, Constants.REAUTHORIZATION, refreshToken, jwtUtil.getRefreshTokenExpiration());

        if (user.getEUserType() == EUserType.GUEST) {
            //이동시킬 페이지 주소
            response.sendRedirect("SIGNUP_URL");
        } else {
            response.sendRedirect("MAIN_URL");
        }
    }
}
