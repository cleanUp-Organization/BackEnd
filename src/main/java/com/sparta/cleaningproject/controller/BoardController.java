package com.sparta.cleaningproject.controller;

import com.sparta.cleaningproject.dto.BoardRequestDto;
import com.sparta.cleaningproject.dto.BoardResponseDto;
import com.sparta.cleaningproject.dto.MessageResponseDto;
import com.sparta.cleaningproject.security.UserDetailsImpl;
import com.sparta.cleaningproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    @PostMapping("/post")
    public MessageResponseDto createBoard(@RequestBody BoardRequestDto boardRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.createBoard(userDetails.getUser(), boardRequestDto);
    }
    @GetMapping("/posts")
    public List<BoardResponseDto> getBoards() {
        return boardService.getBoards();
    }
    @GetMapping("/post/{id}")
    public BoardResponseDto getIdBoard(@PathVariable Long id) {
        return boardService.getIdBoard(id);
    }

    @PutMapping("/post/{id}")
    public BoardResponseDto updateBoard(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody BoardRequestDto boardRequestDto) {
        return boardService.update(userDetails.getUser(), id, boardRequestDto);
    }
    @DeleteMapping("post/{id}")
    public MessageResponseDto deleteBoard(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.deleteBoard(userDetails.getUser(), id);
    }
}
