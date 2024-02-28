package com.example.demo.dto.authDto;

import com.example.demo.type.LoginType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserJoinRequest {
    private String userId;
    private String userName;
    private LoginType userType;
    private String userEmail;
}
