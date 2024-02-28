package com.example.demo.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LoginType {

    KKO("kakao"),
    GGL("google");

    private final String value;
}
