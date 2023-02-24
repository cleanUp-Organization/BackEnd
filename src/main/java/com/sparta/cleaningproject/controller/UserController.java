package com.sparta.cleaningproject.controller;

import com.sparta.cleaningproject.dto.LoginRequestDto;
import com.sparta.cleaningproject.dto.MessageResponseDto;
import com.sparta.cleaningproject.dto.UserRequestDto;
import com.sparta.cleaningproject.security.UserDetailsImpl;
import com.sparta.cleaningproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/api/user/signup")
    public MessageResponseDto signup(@Valid @RequestBody UserRequestDto userRequestDto) {
        System.out.println("시작 전");
        return userService.signup(userRequestDto);
    }

    @PostMapping ("/api/user/login")
    public MessageResponseDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return userService.login(loginRequestDto, response);
    }

    @DeleteMapping("/api/withdrawal")
    public MessageResponseDto withdrawal (@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.withdrawal(userDetails.getUser());
    }

}
