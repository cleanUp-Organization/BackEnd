package com.sparta.cleaningproject.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseMsgDto {
    private String msg;
    private int statusCode;
    @Builder
    public ResponseMsgDto(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
