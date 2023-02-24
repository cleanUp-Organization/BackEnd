package com.sparta.cleaningproject.service;

import com.sparta.cleaningproject.dto.MessageResponseDto;
import com.sparta.cleaningproject.entity.Board;
import com.sparta.cleaningproject.entity.BoardLikes;
import com.sparta.cleaningproject.entity.User;
import com.sparta.cleaningproject.repository.BoardLikesRepository;
import com.sparta.cleaningproject.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikesService {
    private final BoardRepository boardRepository;
    private final BoardLikesRepository boardLikesRepository;


    public MessageResponseDto boardLike(User user, Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        BoardLikes boardLikesBoardUser = boardLikesRepository.findByBoardIdAndUserId(boardId,user.getId());
        if (boardLikesBoardUser == null) {
            BoardLikes boardLikes = BoardLikes.builder()
                    .board(board)
                    .user(user)
                    .build();
            boardLikesRepository.save(boardLikes);
            return MessageResponseDto.builder()
                    .msg("좋아요 성공!")
                    .statusCode(HttpStatus.OK)
                    .build();
        } else {
            boardLikesRepository.delete(boardLikesBoardUser);
            return MessageResponseDto.builder()
                    .msg("좋아요 취소 성공!")
                    .statusCode(HttpStatus.OK)
                    .build();
        }
    }
}
