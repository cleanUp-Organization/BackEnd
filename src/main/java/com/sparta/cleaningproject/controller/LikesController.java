package com.sparta.cleaningproject.controller;

import com.sparta.cleaningproject.dto.MessageResponseDto;
import com.sparta.cleaningproject.security.UserDetailsImpl;
import com.sparta.cleaningproject.service.LikesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "게시글 좋아요 요청", description = "게시글 Id를 통해 좋아요를 요청합니다.", tags = { "Likes" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
//                    content = @Content(schema = @Schema(implementation = MessageResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/boards/{boardId}/like")
    public MessageResponseDto boardLike(@PathVariable Long boardId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return likesService.boardLike(userDetails.getUser(), boardId);
    }
    @Operation(summary = "댓글 좋아요 요청", description = "댓글 Id를 통해 좋아요를 요청합니다.", tags = { "Likes" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
//                    content = @Content(schema = @Schema(implementation = MessageResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/boards/comments/{commentId}/like")
    public MessageResponseDto commentLike(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return likesService.commentLike(userDetails.getUser(), commentId);
    }
}
