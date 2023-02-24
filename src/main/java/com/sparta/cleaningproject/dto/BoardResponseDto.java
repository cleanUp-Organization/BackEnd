package com.sparta.cleaningproject.dto;

import com.sparta.cleaningproject.entity.Board;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class BoardResponseDto {
    private String username;
    private String title;
    private String imgUrl;
    private LocalDateTime createdAt;

    @Builder
    public BoardResponseDto(Board board) {
        username = board.getUser().getUsername();
        title = board.getTitle();
        imgUrl = board.getImgUrl();
        createdAt = board.getCreatedAt();
    }
}
