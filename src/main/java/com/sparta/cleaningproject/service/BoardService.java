package com.sparta.cleaningproject.service;

import com.sparta.cleaningproject.dto.*;
import com.sparta.cleaningproject.entity.Board;
import com.sparta.cleaningproject.entity.Comment;
import com.sparta.cleaningproject.entity.User;
import com.sparta.cleaningproject.entity.UserRoleEnum;
import com.sparta.cleaningproject.exception.CustomException;
import com.sparta.cleaningproject.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static com.sparta.cleaningproject.exception.Exception.*;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    @Transactional
    public MessageResponseDto createBoard(User user, BoardRequestDto boardRequestDto) {
        Board board = Board.builder()
                .boardRequestDto(boardRequestDto)
                .user(user)
                .build();
        boardRepository.save(board);
        return MessageResponseDto.builder()
                .msg("생성 성공")
                .statusCode(HttpStatus.OK)
                .build();
    }
    @Transactional
    public List<BoardCommentResponseDto> getBoards() {
        List<Board> boards = boardRepository.findAllByOrderByCreatedAtDesc();
        List<BoardCommentResponseDto> boardResponseDto = new ArrayList<>();
        for (Board b : boards) {
            b.getCommentList().sort(Comparator.comparing(Comment::getCreatedAt).reversed());
            List<CommentResponseDto> commentResponseDto = new ArrayList<>();
            for (Comment c : b.getCommentList()) {
                commentResponseDto.add(new CommentResponseDto(c));
            }
            boardResponseDto.add(BoardCommentResponseDto.builder()
                    .board(b)
                    .commentList(commentResponseDto)
                    .build());
        }
        return boardResponseDto;
    }
    @Transactional
    public BoardResponseDto getIdBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new CustomException(NOT_FOUND_BOARD)
        );

        return BoardResponseDto.builder()
                .board(board)
                .build();
    }
    @Transactional
    public BoardResponseDto update(User user, Long id, BoardRequestDto boardRequestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new CustomException(NOT_FOUND_BOARD)
        );
        if (Objects.equals(user.getId(), board.getUser().getId()) || user.getRole() == UserRoleEnum.ADMIN) {
            board.update(boardRequestDto);
            // 요청받은 DTO 로 DB에 저장할 객체 만들기
            return BoardResponseDto.builder()
                    .board(board)
                    .build();
        } else {
            throw new CustomException(AUTHORIZATION);
        }
    }
    @Transactional
    public MessageResponseDto deleteBoard(User user, Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new CustomException(NOT_FOUND_BOARD)
        );
        if (Objects.equals(user.getId(), board.getUser().getId()) || user.getRole() == UserRoleEnum.ADMIN) {
            boardRepository.deleteById(id);
            return MessageResponseDto.builder()
                    .msg("게시글 삭제 성공")
                    .statusCode(HttpStatus.OK)
                    .build();
        } else {
            throw new CustomException(AUTHORIZATION);
        }
    }
}
