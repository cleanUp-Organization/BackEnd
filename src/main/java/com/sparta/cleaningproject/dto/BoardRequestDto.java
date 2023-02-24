package com.sparta.cleaningproject.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardRequestDto {
    private String imgUrl;
    private String title;
    private String content;
}
