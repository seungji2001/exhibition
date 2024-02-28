package com.example.demo.dto.authDto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class SocialUserResponse {
    private String id;
    private String email;
    private String name;
    private String gender;
    private String birthday;
}