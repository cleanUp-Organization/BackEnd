package com.sparta.cleaningproject.controller;

import com.sparta.cleaningproject.dto.CommentRequestDto;
import com.sparta.cleaningproject.dto.CommentResponseDto;
import com.sparta.cleaningproject.dto.MessageResponseDto;
import com.sparta.cleaningproject.security.UserDetailsImpl;
import com.sparta.cleaningproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment/{id}")
    public CommentResponseDto createComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.createComment(id,requestDto,userDetails.getUser());
    }

    @PutMapping("/comment/{id}")
    public CommentResponseDto updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto , @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.updateComment(id,requestDto,userDetails.getUser());
    }

    @DeleteMapping("/comment/[id}")
    private MessageResponseDto deleteComment(@PathVariable Long id , @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.deleteComment(id,userDetails.getUser());
    }
}
