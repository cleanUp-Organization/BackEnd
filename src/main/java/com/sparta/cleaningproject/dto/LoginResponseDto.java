package com.sparta.cleaningproject.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class LoginResponseDto {


    private String msg;
    private HttpStatus statusCode;
    private String jwtUtil;

    @Builder
    public LoginResponseDto(String msg, String jwtUtil, HttpStatus statusCode) {
        this.msg = msg;
        this.jwtUtil = jwtUtil;
        this.statusCode = statusCode;
    }

}

