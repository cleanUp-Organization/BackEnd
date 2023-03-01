package com.sparta.cleaningproject.service;

import com.sparta.cleaningproject.dto.LoginRequestDto;
import com.sparta.cleaningproject.dto.LoginResponseDto;
import com.sparta.cleaningproject.dto.MessageResponseDto;
import com.sparta.cleaningproject.dto.UserRequestDto;
import com.sparta.cleaningproject.entity.User;
import com.sparta.cleaningproject.entity.UserRoleEnum;
import com.sparta.cleaningproject.exception.CustomException;
import com.sparta.cleaningproject.jwt.JwtUtil;
import com.sparta.cleaningproject.repository.BoardRepository;
import com.sparta.cleaningproject.repository.CommentRepository;
import com.sparta.cleaningproject.repository.UserRepository;
import com.sparta.cleaningproject.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.sparta.cleaningproject.exception.Exception.*;
import static com.sparta.cleaningproject.response.ResponseMsg.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final ApiResponse apiResponse;

    @Transactional
    public MessageResponseDto signup(UserRequestDto userRequestDto) {

        String username = userRequestDto.getUsername();
        String password = passwordEncoder.encode(userRequestDto.getPassword());

        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new CustomException(DUPLICATED_USERNAME);
        }
        UserRoleEnum role = UserRoleEnum.USER;

        if (userRequestDto.isAdmin()) {

            if (!userRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {

                throw new CustomException(NOT_FOUND_TOKEN);
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = User.of(username, password, role);

        userRepository.save(user);

        return apiResponse.success(SIGNUP_SUCCESS.getMsg());

    }

    @Transactional(readOnly = true)
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new CustomException(NOT_FOUND_USER)
        );
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new CustomException(INVALID_PASSWORD);
        }

        String jwtUtil2 =JwtUtil.AUTHORIZATION_HEADER+" "+jwtUtil.createToken(user.getUsername(), user.getRole());

        return LoginResponseDto.of(jwtUtil2);

    }

    @Transactional
    public  MessageResponseDto withdrawal(User user) {

        User userCheck = userRepository.findByUsername(user.getUsername()).orElseThrow(
                () -> {
                    throw new CustomException(NOT_FOUND_USER);
                }
        );
        boardRepository.deleteAllByUser(userCheck);
        commentRepository.deleteAllByUser(userCheck);

        userRepository.delete(userCheck);

        return apiResponse.success(WITHDRAWAL_SUCCESS.getMsg());

    }

}
