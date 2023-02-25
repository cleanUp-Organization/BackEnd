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

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    @PostMapping("/board")
    public MessageResponseDto createBoard(@RequestBody BoardRequestDto boardRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.createBoard(userDetails.getUser(), boardRequestDto);
    }
    @GetMapping("/boards")
    public List<BoardCommentResponseDto> getBoards() {
        return boardService.getBoards();
    }
    @GetMapping("/board/{id}")
    public BoardResponseDto getIdBoard(@PathVariable Long id) {
        return boardService.getIdBoard(id);
    }

    @PutMapping("/board/{id}")
    public MessageResponseDto updateBoard(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody BoardRequestDto boardRequestDto) {
        return boardService.update(userDetails.getUser(), id, boardRequestDto);
    }
    @DeleteMapping("board/{id}")
    public MessageResponseDto deleteBoard(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.deleteBoard(userDetails.getUser(), id);
    }
}
