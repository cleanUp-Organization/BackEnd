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
    @PostMapping("/board")
    public MessageResponseDto createBoard(@RequestPart(value = "imgUrl",required = false) MultipartFile multipartFile
            ,@RequestPart(value = "boardRequestDto") BoardRequestDto boardRequestDto
            ,@AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        return boardService.createBoard(userDetails.getUser(), boardRequestDto ,multipartFile);
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
    public MessageResponseDto updateBoard(@RequestPart(value = "imgUrl",required = false) MultipartFile multipartFile ,@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestPart BoardRequestDto boardRequestDto) throws IOException {
        return boardService.update(userDetails.getUser(), id, boardRequestDto,multipartFile);
    }
    @DeleteMapping("board/{id}")
    public MessageResponseDto deleteBoard(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.deleteBoard(userDetails.getUser(), id);
    }
}
