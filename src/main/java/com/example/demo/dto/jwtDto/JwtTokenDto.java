package com.example.demo.dto.jwtDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.io.Serializable;

//record의 경우 자바의 불변 객체를 위한 것이며 모든 필드가 final이 된다.
@Builder
public record JwtTokenDto (
        //JsonProperty를 사용하면 역직렬화, 직렬화가 가능해지며 Json객체에서 access_token키를 가진 값을 accessToken에 매핑이 가능해진다.
        @JsonProperty("access_token")
        @NotNull
        String accessToken,

        @JsonProperty("refresh_token")
        String refreshToken
        //implements serializable을 하면 직렬화 역직렬화가 가능해진다.
) implements Serializable {
    //정적 팩토리 메소드 사용 -> 호출 후 JwtTokenDto로 변환하여 제공
    public static JwtTokenDto of(String accessToken, String refreshToken) {
        return JwtTokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
