package com.sparta.cleaningproject.controller;

import com.sparta.cleaningproject.dto.BoardCommentResponseDto;
import com.sparta.cleaningproject.dto.BoardRequestDto;
import com.sparta.cleaningproject.dto.BoardResponseDto;
import com.sparta.cleaningproject.dto.MessageResponseDto;
import com.sparta.cleaningproject.security.UserDetailsImpl;
import com.sparta.cleaningproject.service.BoardService;
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
    @PostMapping("/boards")
    public MessageResponseDto createBoard(@ModelAttribute BoardRequestDto boardRequestDto
            ,@AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        return boardService.createBoard(userDetails.getUser(), boardRequestDto);
    }
    @GetMapping("/boards")
    public List<BoardCommentResponseDto> getBoards() {
        return boardService.getBoards();
    }

    @GetMapping("/boards/{boardId}")
    public BoardResponseDto getIdBoard(@PathVariable Long boardId) {
        return boardService.getIdBoard(boardId);
    }

    @PutMapping("/boards/{boardId}")
    public MessageResponseDto updateBoard(@PathVariable Long boardId, @AuthenticationPrincipal UserDetailsImpl userDetails, @ModelAttribute BoardRequestDto boardRequestDto) throws IOException {
        return boardService.update(userDetails.getUser(), boardId, boardRequestDto);
    }
    @DeleteMapping("/boards/{boardId}")
    public MessageResponseDto deleteBoard(@PathVariable Long boardId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.deleteBoard(userDetails.getUser(), boardId);
    }
}
