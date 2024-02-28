package com.example.demo.dto.authDto;

import com.example.demo.type.LoginType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SocialLoginRequestDto {
    @NotNull
    private LoginType loginType;
    @NotNull
    private String code;
}
