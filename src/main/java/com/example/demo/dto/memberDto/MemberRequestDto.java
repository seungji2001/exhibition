package com.example.demo.dto.memberDto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

public class MemberRequestDto {
    @Builder
    @Data
    @Getter
    public static class participantsMember{
        private String name;
    }

    @Builder
    @Data
    @Getter
    public static class adminMember{
        private String loginId;
        private String password;
        private String name;
        private String cellphone;
    }

    @Builder
    @Data
    @Getter
    public static class supporterMember{
        private String loginId;
        private String password;
        private String name;
        private String cellphone;
        private String introduction;
        private String major;
    }

    @Builder
    @Data
    @Getter
    public static class loginMember{
        private String loginId;
        private String password;
    }
}
