package com.example.demo.service;

import com.example.demo.domain.client;
import com.example.demo.dto.authDto.*;
import com.example.demo.repository.ClientRepository;
import com.example.demo.type.LoginType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {
    private final List<SocialLoginService> loginServices;
    private final ClientRepository clientRepository;

    public LoginResponse doSocialLogin(SocialLoginRequestDto request) {
        SocialLoginService loginService = this.getLoginService(request.getLoginType());

        SocialAuthResponse socialAuthResponse = loginService.getAccessToken(request.getCode());

        SocialUserResponse socialUserResponse = loginService.getUserInfo(socialAuthResponse.getAccess_token());
        log.info("socialUserResponse {} ", socialUserResponse.toString());

        if (clientRepository.findByLogin(socialUserResponse.getId()).isEmpty()) {
            this.joinUser(
                    UserJoinRequest.builder()
                            .userId(socialUserResponse.getId())
                            .userEmail(socialUserResponse.getEmail())
                            .userName(socialUserResponse.getName())
                            .userType(request.getLoginType())
                            .build()
            );
        }

        client client = clientRepository.findByLogin(socialUserResponse.getId())
                .orElseThrow(() -> new IllegalArgumentException("유저 정보를 찾을 수 없습니다."));

        return LoginResponse.builder()
                .id(client.getId())
                .build();
    }

//    private Long id;
//
//    private String name;
//
//    private String login_id;
//
//    private String password;
//
//    private String email;
    private UserJoinResponse joinUser(UserJoinRequest userJoinRequest) {
        client client = clientRepository.save(
                com.example.demo.domain.client.builder()
                        .login(userJoinRequest.getUserId())
                        .provider(userJoinRequest.getUserType())
                        .email(userJoinRequest.getUserEmail())
                        .name(userJoinRequest.getUserName())
                        .build()
        );

        return UserJoinResponse.builder()
                .id(client.getId())
                .build();
    }

    private SocialLoginService getLoginService(LoginType loginType){
        for (SocialLoginService loginService: loginServices) {
            if (loginType.equals(loginService.getServiceName())) {
                log.info("login service name: {}", loginService.getServiceName());
                return loginService;
            }
        }
        return new LoginServiceImpl();
    }

    public UserResponse getUser(Long id) {
        client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("유저 정보를 찾을 수 없습니다."));

        return UserResponse.builder()
                .id(client.getId())
                .userId(client.getLogin())
                .userEmail(client.getEmail())
                .userName(client.getName())
                .build();
    }
}
