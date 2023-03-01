package com.sparta.cleaningproject.response;

import com.sparta.cleaningproject.dto.MessageResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ApiResponse {
    public MessageResponseDto success(String msg) {
        return MessageResponseDto.builder()
                .msg(msg)
                .statusCode(HttpStatus.OK)
                .build();
    }
}
