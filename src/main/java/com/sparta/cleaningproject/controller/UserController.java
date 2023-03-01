package com.sparta.cleaningproject.controller;

import com.sparta.cleaningproject.dto.LoginRequestDto;
import com.sparta.cleaningproject.dto.LoginResponseDto;
import com.sparta.cleaningproject.dto.MessageResponseDto;
import com.sparta.cleaningproject.dto.UserRequestDto;
import com.sparta.cleaningproject.response.ApiDocumentResponse;
import com.sparta.cleaningproject.security.UserDetailsImpl;
import com.sparta.cleaningproject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@ApiDocumentResponse
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    @Operation(summary = "회원가입 요청", description = "회원가입을 요청합니다.", tags = { "Users" })
    @PostMapping("/signup")
    public MessageResponseDto signup(@Valid @RequestBody UserRequestDto userRequestDto) {
        return userService.signup(userRequestDto);
    }
    @Operation(summary = "로그인 요청", description = "로그인을 요청합니다.", tags = { "Users" })
    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
        return userService.login(loginRequestDto);
    }
    @Operation(summary = "회원탈퇴 요청", description = "회원탈퇴 요청합니다.", tags = { "Users" })
    @DeleteMapping("/withdrawal")
    public MessageResponseDto withdrawal(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.withdrawal(userDetails.getUser());
    }

}
