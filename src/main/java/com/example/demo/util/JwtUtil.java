package com.example.demo.util;


import com.example.demo.constant.Constants;
import com.example.demo.dto.jwtDto.JwtTokenDto;
import com.example.demo.type.EUserType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Getter
@Component
public class JwtUtil implements InitializingBean {
    @Value("${security.jwt.secret}")
    private String secretKey;
    @Value("${security.jwt.access.expiration}")
    private Integer accessTokenExpiration;
    @Value("${security.jwt.refresh.expiration}")
    private Integer refreshTokenExpiration;
    private Key key;

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public JwtTokenDto generateTokens(String socialId, EUserType userType) {
        return new JwtTokenDto(
                generateToken(socialId, userType, accessTokenExpiration * 1000),
                generateToken(socialId, userType, refreshTokenExpiration * 1000)
        );
    }

    public Claims validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private String generateToken(String socialId, EUserType userType, Integer expiration) {
        Claims claims = Jwts.claims();
        claims.put(Constants.SOCIAL_ID_CLAIM_NAME, socialId);
        if (userType != null) {
            claims.put(Constants.USER_TYPE_CLAIM_NAME, userType);
        }

        return Jwts.builder()
                .setHeaderParam(Header.JWT_TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}