package com.sparta.cleaningproject.service;

import com.sparta.cleaningproject.dto.*;
import com.sparta.cleaningproject.entity.*;
import com.sparta.cleaningproject.exception.CustomException;
import com.sparta.cleaningproject.repository.BoardRepository;
import com.sparta.cleaningproject.response.ApiResponse;
import com.sparta.cleaningproject.response.ResponseMsg;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static com.sparta.cleaningproject.exception.Exception.*;
import static com.sparta.cleaningproject.response.ResponseMsg.*;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final S3Uploader s3Uploader;
    private final ApiResponse apiResponse;
    @Transactional
    public MessageResponseDto createBoard(User user, BoardRequestDto boardRequestDto) throws IOException {
        String imgUrl = "";
        if (boardRequestDto.getImgUrl() == null) {
             imgUrl = "https://cleaningproject.s3.ap-northeast-2.amazonaws.com/static/%EB%8F%99%EA%B7%B8%EB%9D%BC%EB%AF%B8%20%EC%B2%AD%EC%86%8C.png";
        } else {
             imgUrl = s3Uploader.upload(boardRequestDto.getImgUrl());
        }
        Board board = Board.builder()
                .boardRequestDto(boardRequestDto)
                .user(user)
                .imgUrl(imgUrl)
                .build();
        boardRepository.save(board);
        return apiResponse.success(CONSTRUCT_SUCCESS.getMsg());
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
    public BoardResponseDto getIdBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new CustomException(NOT_FOUND_BOARD)
        );
        board.getCommentList().sort(Comparator.comparing(Comment::getCreatedAt).reversed());
        List<CommentResponseDto> commentResponseDto = new ArrayList<>();
        for (Comment c : board.getCommentList()) {
            commentResponseDto.add(new CommentResponseDto(c));
        }
        return BoardResponseDto.builder()
                .board(board)
                .commentList(commentResponseDto)
                .build();
    }
    @Transactional
    public MessageResponseDto update(User user, Long boardId, BoardRequestDto boardRequestDto) throws IOException {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new CustomException(NOT_FOUND_BOARD)
        );
        String imgUrl = "";
        if (boardRequestDto.getImgUrl() == null) {
            imgUrl = "https://cleaningproject.s3.ap-northeast-2.amazonaws.com/static/%EB%8F%99%EA%B7%B8%EB%9D%BC%EB%AF%B8%20%EC%B2%AD%EC%86%8C.png";
        } else {
            imgUrl = s3Uploader.upload(boardRequestDto.getImgUrl());
        }
        if (Objects.equals(user.getId(), board.getUser().getId()) || user.getRole() == UserRoleEnum.ADMIN) {
            board.update(boardRequestDto,imgUrl);
            // 요청받은 DTO 로 DB에 저장할 객체 만들기
            return apiResponse.success(BOARD_UPDATE_SUCCESS.getMsg());
        } else {
            throw new CustomException(AUTHORIZATION);
        }
    }
    @Transactional
    public MessageResponseDto deleteBoard(User user, Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new CustomException(NOT_FOUND_BOARD)
        );
        if (Objects.equals(user.getId(), board.getUser().getId()) || user.getRole() == UserRoleEnum.ADMIN) {
            boardRepository.deleteById(boardId);
            return apiResponse.success(BOARD_DELETE_SUCCESS.getMsg());
        } else {
            throw new CustomException(AUTHORIZATION);
        }
    }
}
