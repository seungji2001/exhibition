package com.example.demo.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class OAuth2Util {
    //application.properties에 저장한 google api 정보 저장하기
    @Value("${security.oauth2.google.authentication_url}")
    private String GOOGLE_AUTHENTICATION_URL;
    @Value("${security.oauth2.google.token_url}")
    private String GOOGLE_TOKEN_URL;
    @Value("${security.oauth2.google.user_info_url}")
    private String GOOGLE_USER_INFO_URL;
    @Value("${security.oauth2.google.client_id}")
    private String GOOGLE_CLIENT_ID;
    @Value("${security.oauth2.google.client_secret}")
    private String GOOGLE_CLIENT_SECRET;
    @Value("${security.oauth2.google.redirect_uri}")
    private String GOOGLE_REDIRECT_URI;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getGoogleRedirectUrl() {
        return GOOGLE_AUTHENTICATION_URL
                + "?client_id=" + GOOGLE_CLIENT_ID
                + "&redirect_uri=" + GOOGLE_REDIRECT_URI
                + "&response_type=code&scope=https://www.googleapis.com/auth/userinfo.profile";
    }

    //리다이렉트 받은 코드를 가지고 토큰을 얻어온다 -> 해당 토큰으로 소셜로그인 정보를 받아올 수 있다.
    public String getGoogleAccessToken(String code) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        /*
        HashMap: 가장 일반적으로 사용되는 Map 구현체입니다. 해시 테이블을 사용하여 키와 값을 저장합니다. 하나의 null 키와 여러 null 값을 허용합니다. 동기화되지 않으며 항목의 순서를 보장하지 않습니다.
        LinkedHashMap: 해시 테이블과 연결 리스트 구현을 사용하는 Map 인터페이스의 구현체입니다. 모든 항목을 삽입 순서로 유지합니다. 이 연결 리스트는 반복 순서를 정의하며, 일반적으로 삽입 순서(insertion-order)입니다. 하나의 null 키와 여러 null 값을 허용합니다.
          TreeMap: 레드-블랙 트리 기반의 Map 구현체입니다. 키를 정렬된 순서로 저장합니다. null 키는 허용하지 않지만, 여러 null 값을 허용합니다. 키를 정렬된 순서로 유지해야 하는 경우에 적합합니다.
        ConcurrentHashMap: HashMap의 스레드 안전한 변형입니다. 여러 스레드에 대한 동시 접근을 허용합니다. null 키는 허용하지 않지만, 여러 null 값을 허용합니다. 여러 스레드에서 맵에 동시에 접근할 때 적합합니다.
        Hashtable: Java의 초기 시절에 사용되던 레거시 Map 구현체입니다. 동기화되며 null 키나 null 값을 허용하지 않습니다. 여전히 사용되지만, ConcurrentHashMap보다 느리고 메모리 효율성이 떨어집니다.
        Properties: Hashtable의 하위 클래스로, 키-값 쌍을 영구적으로 저장하는 데 사용됩니다. 주로 구성 목적으로 사용됩니다.
         */
        MultiValueMap<String, String> params = new org.springframework.util.LinkedMultiValueMap<>();
        //모든 항목을 삽입 순서로 유지합니다
        params.add("grant_type", "authorization_code");
        params.add("client_id", GOOGLE_CLIENT_ID);
        params.add("client_secret", GOOGLE_CLIENT_SECRET);
        params.add("redirect_uri", GOOGLE_REDIRECT_URI);
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> googleTokenRequest = new HttpEntity<>(params, httpHeaders);
        /*
        HttpEntity<body type> g = new HttpEntity<>(body, MultiValueMap<String, String> HttpHeader는 MultiValueMap이다.)
        MultiValueMap은 하나의 키에 대해 0개 이상의 값들을 가질 수 있다.
         */

/*
HttpEntity는 Spring Framework에서 HTTP 요청과 응답을 처리할 때 사용되는 클래스로, 요청 헤더와 본문을 포함하는 컨테이너 객체입니다. 이를 사용하면 HTTP 요청이나 응답의 헤더와 본문을 쉽게 처리할 수 있습니다.
HttpEntity(): 새로운, 빈 HttpEntity를 생성합니다.
HttpEntity(T body): 주어진 본문(body)을 가진 새로운 HttpEntity를 생성합니다. 헤더는 없습니다.
HttpEntity(MultiValueMap<String, String> headers): 주어진 헤더(headers)를 가진 새로운 HttpEntity를 생성합니다. 본문은 없습니다.
HttpEntity(@Nullable T body, @Nullable MultiValueMap<String, String> headers): 주어진 본문(body)과 헤더(headers)를 가진 새로운 HttpEntity를 생성합니다.
 */
        ResponseEntity<String> response = restTemplate.exchange(
                GOOGLE_TOKEN_URL,
                org.springframework.http.HttpMethod.POST,
                googleTokenRequest,
                String.class
        );
        /*
        google_token_url로 Post방법을 지정하여 googleTokenRequest(헤더와 바디 -> 토큰을 받아올때 필요한정보)를 보냅니다.
        응답 타입은 String타입으로 받습니다.
         */

        return JsonParser.parseString(Objects.requireNonNull(response.getBody())).getAsJsonObject().get("access_token").getAsString();
    }

    public String getGoogleUserInfo(String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Authorization", "Bearer " + accessToken);
        httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> googleUserInfoRequest = new HttpEntity<>(httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(
                GOOGLE_USER_INFO_URL,
                org.springframework.http.HttpMethod.GET,
                googleUserInfoRequest,
                String.class
        );

        JsonElement element = JsonParser.parseString(Objects.requireNonNull(response.getBody()));
        System.out.println(element.getAsJsonObject());
        return element.getAsJsonObject().get("id").getAsString();
    }
}

