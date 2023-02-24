package com.sparta.cleaningproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardRequestDto {
    private String imgUrl;
    private String title;
    private String content;
}
