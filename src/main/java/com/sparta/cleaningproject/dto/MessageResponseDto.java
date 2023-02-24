package com.sparta.cleaningproject.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MessageResponseDto {
    private String msg;
    private int statusCode;
    @Builder
    public MessageResponseDto(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
