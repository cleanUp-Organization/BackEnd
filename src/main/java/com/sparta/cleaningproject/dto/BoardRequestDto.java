package com.sparta.cleaningproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class BoardRequestDto {
    MultipartFile imgUrl;
    private String title;
    private String content;
}