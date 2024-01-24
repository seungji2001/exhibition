package com.example.demo.Enum;

public enum LoginType {
    KKO("kakao"),
    GGL("google");

    private String value;

    private LoginType(String status) {
        this.value = status;
    }
    public String getValue(){
        return value;
    }
}
