package com.sparta.cleaningproject.service;

import com.sparta.cleaningproject.dto.LoginRequestDto;
import com.sparta.cleaningproject.dto.MessageResponseDto;
import com.sparta.cleaningproject.dto.UserRequestDto;
import com.sparta.cleaningproject.entity.User;
import com.sparta.cleaningproject.entity.UserRoleEnum;
import com.sparta.cleaningproject.exception.CustomException;
import com.sparta.cleaningproject.jwt.JwtUtil;
import com.sparta.cleaningproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.sparta.cleaningproject.exception.Exception.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MessageResponseDto signup(UserRequestDto userRequestDto) {

        String username = userRequestDto.getUsername();
        String password = passwordEncoder.encode(userRequestDto.getPassword());

        Optional<User> found = userRepository.findByUsername(username);

        if(found.isPresent()) {
            throw new CustomException(DUPLICATED_USERNAME);
        }

        UserRoleEnum role = UserRoleEnum.USER;

        if (userRequestDto.isAdmin()) {

            if (!userRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new CustomException(DUPLICATED_USERNAME);
            }

            
            role = UserRoleEnum.ADMIN;
        }

        User user = User.builder()
                .username(username)
                .password(password)
                .role(role)
                .build();

        userRepository.save(user);

        return MessageResponseDto.builder()
                .msg("정상적으로 회원이 완료되었습니다.")
                .statusCode(HttpStatus.OK)
                .build();

    }


    @Transactional(readOnly = true)
    public  MessageResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse response) {

        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new CustomException(NOT_FOUND_USER)
        );

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new CustomException(INVALID_PASSWORD);
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(), user.getRole()));

        return MessageResponseDto.builder()
                .msg("로그인 성공!")
                .statusCode(HttpStatus.OK)
                .build();
    }

    @Transactional
    public MessageResponseDto withdrawal(User user) {

        String username = user.getUsername();

        User userCheck = userRepository.findByUsername(username).orElseThrow(
                () -> new CustomException(NOT_FOUND_USER)
        );

        userRepository.delete(userCheck);

        return MessageResponseDto.builder()
                .msg("회원 탈퇴 성공!")
                .statusCode(HttpStatus.OK)
                .build();


    }


}
