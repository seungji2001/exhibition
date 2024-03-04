package com.example.demo.domain;

import com.example.demo.type.EUserType;
import com.example.demo.type.LoginType;
import jakarta.persistence.*;
import jdk.jfr.Description;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_user")
public class User {

    @Id
    @Column(name = "tb_user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //여기서 login은 소셜 로그인으로 받아온 temp_id이다.
    private String login;

    private String refreshToken;

    private Boolean isLogin;

    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    private LoginType provider;

    @Enumerated(EnumType.STRING)
    private EUserType eUserType;

    @Description("client가 어떤 전시들에 연관이 있는지 보여줌")
    @OneToMany(mappedBy = "user")
    private List<client_exhibition> exhibitions;

    public void updateRefreshToken(String refreshToken) {
        this.isLogin = true;
        this.refreshToken = refreshToken;
    }
}
