package com.example.demo.security.jwt;


import com.example.demo.constant.Constants;
import com.example.demo.domain.User;
import com.example.demo.dto.jwtDto.JwtTokenDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.JwtUtil;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

//component 어노테이션 사용시
@Component
@RequiredArgsConstructor
/*
이 예제에서 CustomerService 클래스는 InitializingBean 인터페이스를 구현하여 afterPropertiesSet() 메서드를 오버라이드합니다. 이 메서드 내에서 메시지를 출력하는 로직이 정의되어 있으며, 이는 빈의 모든 속성이 설정된 후에 실행됩니다 2.

InitializingBean 인터페이스는 빈의 초기화 과정을 커스터마이징할 수 있게 해주는 유용한 방법입니다. 이는 빈의 생명주기 관리를 더욱 세밀하게 제어할 수 있게 해줍니다 13.
 */
public class JwtProvider implements InitializingBean {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private Key key;

    @Override
    public void afterPropertiesSet() throws Exception {
        byte [] keyBytes = Decoders.BASE64.decode(jwtUtil.getSecretKey());
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(String socialId) {
        //클레임은 JWT의 페이로드(payload) 부분에 포함되는 주장이나 정보를 의미하며, 이는 토큰의 주체(subject)에 대한 정보를 포함할 수 있습니다
        Claims claims = Jwts.claims();

        //claim에 social id 에 대한 정보를 넣어줌
        claims.put(Constants.SOCIAL_ID_CLAIM_NAME, socialId);

        //어떻게 반환되는지 확인하기
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                //토큰 발행 시간
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (
                        jwtUtil.getAccessTokenExpiration()
                )))
                //토큰에 서명을 추가하는데 사용, 서명을 위한 key와 사용할 서명 알고리즘 이 인수로 들어감
                //토큰을 생성한 서버만이 해당 토큰을 검증할 수 있도록 한다.
                .signWith(key, SignatureAlgorithm.HS256)
                //문자열 형태로 출력하는데 사용
                .compact();
    }

    public JwtTokenDto createTokens(String socialId) {
        return new JwtTokenDto(
                createToken(socialId),
                createToken(socialId)
        );
    }

//    public String validateRefreshToken(HttpServletRequest request) {
//        String refreshToken = resolveToken(request);
//        Claims claims = validateToken(refreshToken);
//
//        User user = userRepository.findBySocialIdAndRefreshToken(claims.get(Constants.SOCIAL_ID_CLAIM_NAME, String.class), refreshToken)
//                .orElseThrow(() -> new JwtException("USER_NOT_FOUND"));
//        return createToken(user.getSocialId());
//    }

    public String getSocialId(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get(Constants.SOCIAL_ID_CLAIM_NAME, String.class);
    }

    public Claims validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static String resolveToken(HttpServletRequest request) {
        String token = request.getHeader(Constants.AUTHORIZATION);
        String newToken = null;

        if (token != null && token.startsWith(Constants.BEARER_PREFIX)) {
            newToken = token.substring(7);
        }
        return newToken;
    }
}