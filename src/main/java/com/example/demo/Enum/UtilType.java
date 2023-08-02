package com.example.demo.Enum;

public enum UtilType {
    TITLE("제목"),
    CONTENT("내용");

    private String value;

    private UtilType(String status) {
        this.value = status;
    }
    public String getValue(){
        return value;
    }
}
