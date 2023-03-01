package com.sparta.cleaningproject.response;

import com.sparta.cleaningproject.dto.BoardRequestDto;
import com.sparta.cleaningproject.dto.MessageResponseDto;
import com.sparta.cleaningproject.entity.Board;
import com.sparta.cleaningproject.service.S3Uploader;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Getter
@Component
@RequiredArgsConstructor
public class ApiResponse {
    private final S3Uploader s3Uploader;
    public MessageResponseDto success(String msg) {
        return MessageResponseDto.builder()
                .msg(msg)
                .statusCode(HttpStatus.OK)
                .build();
    }

    public String  checkNullBoardRequestDto(Board board, BoardRequestDto boardRequestDto, String imgUrl) throws IOException {
        if (boardRequestDto.getImgUrl() == null||boardRequestDto.getImgUrl().isEmpty()) {
            imgUrl = board.getImgUrl();
        } else {
            imgUrl = s3Uploader.upload(boardRequestDto.getImgUrl());
        }
        if (boardRequestDto.getTitle() == null) {
            boardRequestDto.setTitle(board.getTitle());
        }
        if (boardRequestDto.getContent() == null) {
            boardRequestDto.setContent(board.getContent());
        }
        return imgUrl;
    }
}
