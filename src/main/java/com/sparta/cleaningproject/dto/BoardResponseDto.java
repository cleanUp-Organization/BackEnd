package com.sparta.cleaningproject.dto;

import com.sparta.cleaningproject.entity.Board;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class BoardResponseDto {
    @Parameter(required = true)
    private String username;
    private String title;
    private String content;
    private String imgUrl;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private int likeNum;
    private List<CommentResponseDto> commentList;

    @Builder
    public BoardResponseDto(Board board,List<CommentResponseDto> commentList) {
        username = board.getUser().getUsername();
        title = board.getTitle();
        imgUrl = board.getImgUrl();
        content = board.getContent();
        createdAt = board.getCreatedAt();
        likeNum = board.getBoardLikes().size();
        modifiedAt = board.getModifiedAt();
        this.commentList = commentList;
    }

    public static BoardResponseDto of(Board board, List<CommentResponseDto> commentResponseDto) {
        return BoardResponseDto.builder()
                .board(board)
                .commentList(commentResponseDto)
                .build();
    }
}