package com.sparta.cleaningproject.controller;

import com.sparta.cleaningproject.dto.CommentRequestDto;
import com.sparta.cleaningproject.dto.MessageResponseDto;
import com.sparta.cleaningproject.response.ApiDocumentResponse;
import com.sparta.cleaningproject.security.UserDetailsImpl;
import com.sparta.cleaningproject.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
@ApiDocumentResponse
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class CommentController {
    private final CommentService commentService;
    @Operation(summary = "댓글 생성 요청", description = "특정 게시글에 댓글을 생성합니다.", tags = { "Comment" })
    @PostMapping("/{boardId}/comments")
    public MessageResponseDto createComment(@PathVariable Long boardId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.createComment(boardId,requestDto,userDetails.getUser());
    }
    @Operation(summary = "댓글 수정 요청", description = "댓글 Id를 통해 수정합니다.", tags = { "Comment" })
    @PutMapping("/comments/{commentId}")
    public MessageResponseDto updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto requestDto , @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.updateComment(commentId,requestDto,userDetails.getUser());
    }
    @Operation(summary = "댓글 삭제 요청", description = "댓글 Id를 통해 삭제합니다.", tags = { "Comment" })
    @DeleteMapping("/comments/{commentId}")
    private MessageResponseDto deleteComment(@PathVariable Long commentId , @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.deleteComment(commentId,userDetails.getUser());
    }
}
