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

    public static LoginResponseDto of(String  msg , HttpStatus statusCode , String jwtUtil2){
        return builder()
                .msg("로그인 성공")
                .statusCode(HttpStatus.OK)
                .jwtUtil(jwtUtil2)
                .build();
    }

}

