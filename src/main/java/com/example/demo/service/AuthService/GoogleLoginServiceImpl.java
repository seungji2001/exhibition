package com.example.demo.service.AuthService;


import com.example.demo.dto.authDto.GoogleLoginResponse;
import com.example.demo.dto.authDto.GoogleRequestAccessTokenDto;
import com.example.demo.dto.authDto.SocialAuthResponse;
import com.example.demo.dto.authDto.SocialUserResponse;
import com.example.demo.service.SocialLoginService;
import com.example.demo.type.LoginType;
import com.example.demo.util.GsonLocalDateTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
@Qualifier("googleLogin")
public class GoogleLoginServiceImpl implements SocialLoginService {
    private final GoogleAuthApi googleAuthApi;
    private final GoogleUserApi googleUserApi;

    @Value("${security.oauth2.google.client_id}")
    private String googleAppKey;
    @Value("${security.oauth2.google.client_secret}")
    private String googleAppSecret;
    @Value("${security.oauth2.google.redirect_uri}")
    private String googleRedirectUri;
//    @Value("${social.client.google.grant_type}")
//    private String googleGrantType;

    @Override
    public LoginType getServiceName() {
        return LoginType.GGL;
    }

    @Override
    public SocialAuthResponse getAccessToken(String authorizationCode) {
        ResponseEntity<?> response = googleAuthApi.getAccessToken(
                GoogleRequestAccessTokenDto.builder()
                        .code(authorizationCode)
                        .client_id(googleAppKey)
                        .clientSecret(googleAppSecret)
                        .redirect_uri(googleRedirectUri)
                        .grant_type("authorization_code")
                        .build()
        );

        log.info("google auth info");
        log.info(response.toString());

        return new Gson()
                .fromJson(
                        response.getBody().toString(),
                        SocialAuthResponse.class
                );
    }

    @Override
    public SocialUserResponse getUserInfo(String accessToken) {
        ResponseEntity<?> response = googleUserApi.getUserInfo(accessToken);

        log.info("google user response");
        log.info(response.toString());

        String jsonString = response.getBody().toString();

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter())
                .create();

        GoogleLoginResponse googleLoginResponse = gson.fromJson(jsonString, GoogleLoginResponse.class);

        return SocialUserResponse.builder()
                .id(googleLoginResponse.getId())
                .email(googleLoginResponse.getEmail())
                .build();
    }
}
