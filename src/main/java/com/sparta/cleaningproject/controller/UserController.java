package com.sparta.cleaningproject.controller;

import com.sparta.cleaningproject.dto.LoginRequestDto;
import com.sparta.cleaningproject.dto.LoginResponseDto;
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
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public MessageResponseDto signup(@Valid @RequestBody UserRequestDto userRequestDto) {
        return userService.signup(userRequestDto);
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
        return userService.login(loginRequestDto);
    }

    @DeleteMapping("/withdrawal")
    public MessageResponseDto withdrawal(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.withdrawal(userDetails.getUser());
    }

}
