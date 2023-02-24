package com.sparta.cleaningproject.dto;

import lombok.Getter;

import javax.validation.constraints.Pattern;

@Getter
public class UserRequestDto {

    @Pattern(regexp = "^[a-z[0-9]]{4,10}$", message = "아이디는 영어 소문자와 숫자만 사용하여 4~10자리여야 합니다")
    private String username;

    @Pattern(regexp =  "^[A-Za-z[0-9][$@$!%*#?&]]{8,15}$", message = "비밀번호는 영어 대소문자와 숫자만 사용하여 8~16자리여야 합니다")
    private String password;

    private boolean admin = false;

    private String adminToken = "";

}
