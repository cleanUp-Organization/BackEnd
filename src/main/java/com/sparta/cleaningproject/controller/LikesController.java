package com.sparta.cleaningproject.controller;

import com.sparta.cleaningproject.dto.MessageResponseDto;
import com.sparta.cleaningproject.security.UserDetailsImpl;
import com.sparta.cleaningproject.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LikesController {
    private final LikesService likesService;
    @PostMapping("/boards/{boardId}/like")
    public MessageResponseDto boardLike(@PathVariable Long boardId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return likesService.boardLike(userDetails.getUser(), boardId);
    }

    @PostMapping("/boards/comments/{commentId}/like")
    public MessageResponseDto commentLike(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return likesService.commentLike(userDetails.getUser(), commentId);
    }
}
