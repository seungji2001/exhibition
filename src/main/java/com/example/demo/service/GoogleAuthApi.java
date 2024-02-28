package com.example.demo.service;

import com.example.demo.dto.authDto.GoogleRequestAccessTokenDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "googleAuth", url="https://oauth2.googleapis.com", configuration = {FeignClientsConfiguration.class})
public interface GoogleAuthApi {

    @PostMapping("/token")
    ResponseEntity<String> getAccessToken(@RequestBody GoogleRequestAccessTokenDto requestAccessTokenDto);
}
