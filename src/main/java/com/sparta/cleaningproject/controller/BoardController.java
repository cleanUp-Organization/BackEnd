package com.sparta.cleaningproject.controller;

import com.sparta.cleaningproject.dto.BoardCommentResponseDto;
import com.sparta.cleaningproject.dto.BoardRequestDto;
import com.sparta.cleaningproject.dto.BoardResponseDto;
import com.sparta.cleaningproject.dto.MessageResponseDto;
import com.sparta.cleaningproject.security.UserDetailsImpl;
import com.sparta.cleaningproject.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    @Operation(summary = "게시글 생성 요청", description = "게시글이 추가됩니다.", tags = { "Board" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = MessageResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/boards")
    public MessageResponseDto createBoard(@ModelAttribute BoardRequestDto boardRequestDto
            ,@AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        return boardService.createBoard(userDetails.getUser(), boardRequestDto);
    }
    @Operation(summary = "게시글 리스트 요청", description = "게시글 리스트를 불러옵니다.", tags = { "Board" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
//                    content = @Content(schema = @Schema(implementation = MessageResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/boards")
    public List<BoardCommentResponseDto> getBoards() {
        return boardService.getBoards();
    }
    @Operation(summary = "게시글 상세 페이지 요청", description = "게시글 상세페이지를 불러옵니다.", tags = { "Board" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
//                    content = @Content(schema = @Schema(implementation = MessageResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/boards/{boardId}")
    public BoardResponseDto getIdBoard(@PathVariable Long boardId) {
        return boardService.getIdBoard(boardId);
    }
    @Operation(summary = "게시글 수정 요청", description = "게시글 Id를 통해 수정합니다.", tags = { "Board" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
//                    content = @Content(schema = @Schema(implementation = MessageResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PatchMapping("/boards/{boardId}")
    public MessageResponseDto updateBoard(@PathVariable Long boardId, @AuthenticationPrincipal UserDetailsImpl userDetails, @ModelAttribute BoardRequestDto boardRequestDto) throws IOException {
        return boardService.update(userDetails.getUser(), boardId, boardRequestDto);
    }
    @Operation(summary = "게시글 삭제 요청", description = "게시글 Id를 통해 삭제합니다.", tags = { "Board" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
//                    content = @Content(schema = @Schema(implementation = MessageResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @DeleteMapping("/boards/{boardId}")
    public MessageResponseDto deleteBoard(@PathVariable Long boardId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.deleteBoard(userDetails.getUser(), boardId);
    }
}
