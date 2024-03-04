package com.example.demo.type;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum EUserType {
    GUEST("GUEST", "ROLE_GUEST"),
    USER("USER", "ROLE_USER");

    private final String name;
    private final String authority;

    public static EUserType fromName(String name) {
        return Arrays.stream(EUserType.values())
                .filter(eUserType -> eUserType.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No such name in EUserType: " + name));
    }
}
