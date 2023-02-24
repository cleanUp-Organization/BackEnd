package com.sparta.cleaningproject.service;

import com.sparta.cleaningproject.dto.CommentRequestDto;
import com.sparta.cleaningproject.dto.CommentResponseDto;
import com.sparta.cleaningproject.entity.Board;
import com.sparta.cleaningproject.entity.Comment;
import com.sparta.cleaningproject.entity.User;
import com.sparta.cleaningproject.exception.CustomException;
import com.sparta.cleaningproject.repository.BoardRepository;
import com.sparta.cleaningproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.sparta.cleaningproject.exception.Exception.NOT_FOUND_BOARD;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public ResponseEntity<CommentResponseDto> createComment(Long id, CommentRequestDto requestDto, User user){

        Board board = boardRepository.findById(id).orElseThrow(
                () -> new CustomException(NOT_FOUND_BOARD)
        );
        Comment comment =  commentRepository.save(Comment.builder()
                .contents(requestDto.getContents())
                .board(board)
                .user(user).build());;
        return ResponseEntity.ok().body(new CommentResponseDto(comment));
    }
}
