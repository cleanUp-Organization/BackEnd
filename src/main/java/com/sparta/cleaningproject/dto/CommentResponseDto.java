package com.sparta.cleaningproject.dto;

import com.sparta.cleaningproject.entity.Comment;
import lombok.Builder;

import java.time.LocalDateTime;

public class CommentResponseDto {
    private Long id;
    private String contents;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    public CommentResponseDto(Comment comment){
        id = comment.getId();
        contents = comment.getContents();
        username = comment.getUser().getUsername();
        createdAt = comment.getCreatedAt();
        modifiedAt = comment.getModifiedAt();
    }
}
