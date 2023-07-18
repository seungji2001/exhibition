package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MemberController {
    @GetMapping(value = "/test")
    public String test() {

        return "home/member";
    }
}
