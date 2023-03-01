package com.sparta.cleaningproject.dto;

import com.sparta.cleaningproject.jwt.JwtUtil;
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

    public static LoginResponseDto of(String  jwtUtil) {
        return LoginResponseDto.builder()
                .msg("로그인 성공")
                .statusCode(HttpStatus.OK)
                .jwtUtil(jwtUtil)
                .build();
    }
}

