package com.sparta.cleaningproject.controller;

import com.sparta.cleaningproject.dto.CommentRequestDto;
import com.sparta.cleaningproject.dto.MessageResponseDto;
import com.sparta.cleaningproject.security.UserDetailsImpl;
import com.sparta.cleaningproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{boardId}/comments")
    public MessageResponseDto createComment(@PathVariable Long boardId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.createComment(boardId,requestDto,userDetails.getUser());
    }

    @PutMapping("/comments/{commentId}")
    public MessageResponseDto updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto requestDto , @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.updateComment(commentId,requestDto,userDetails.getUser());
    }

    @DeleteMapping("/comments/{commentId}")
    private MessageResponseDto deleteComment(@PathVariable Long commentId , @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.deleteComment(commentId,userDetails.getUser());
    }
}
