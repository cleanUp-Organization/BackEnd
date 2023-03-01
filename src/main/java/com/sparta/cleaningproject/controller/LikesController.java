package com.sparta.cleaningproject.controller;

import com.sparta.cleaningproject.dto.MessageResponseDto;
import com.sparta.cleaningproject.response.ApiDocumentResponse;
import com.sparta.cleaningproject.security.UserDetailsImpl;
import com.sparta.cleaningproject.service.LikesService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@ApiDocumentResponse
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LikesController {
    private final LikesService likesService;
    @Operation(summary = "게시글 좋아요 요청", description = "게시글 Id를 통해 좋아요를 요청합니다.", tags = { "Likes" })
    @PostMapping("/boards/{boardId}/like")
    public MessageResponseDto boardLike(@PathVariable Long boardId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return likesService.boardLike(userDetails.getUser(), boardId);
    }
    @Operation(summary = "댓글 좋아요 요청", description = "댓글 Id를 통해 좋아요를 요청합니다.", tags = { "Likes" })
    @PostMapping("/boards/comments/{commentId}/like")
    public MessageResponseDto commentLike(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return likesService.commentLike(userDetails.getUser(), commentId);
    }
}
