package com.example.demo.domain;

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
@Entity(name = "client")
public class client {

    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String login;

    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    private LoginType provider;

    @Description("client가 어떤 전시들에 연관이 있는지 보여줌")
    @OneToMany(mappedBy = "client")
    private List<client_exhibition> exhibitions;
}
