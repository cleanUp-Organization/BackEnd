package com.sparta.cleaningproject.service;

import com.sparta.cleaningproject.dto.MessageResponseDto;
import com.sparta.cleaningproject.entity.*;
import com.sparta.cleaningproject.exception.CustomException;
import com.sparta.cleaningproject.exception.Exception;
import com.sparta.cleaningproject.repository.BoardLikesRepository;
import com.sparta.cleaningproject.repository.BoardRepository;
import com.sparta.cleaningproject.repository.CommentLikesRepository;
import com.sparta.cleaningproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class LikesService {
    private final BoardRepository boardRepository;
    private final BoardLikesRepository boardLikesRepository;
    private final CommentRepository commentRepository;
    private final CommentLikesRepository commentLikesRepository;

    @Transactional
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
    @Transactional
    public MessageResponseDto commentLike(User user , Long commentId){
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new CustomException(Exception.NOT_FOUND_COMMENT)
        );
        CommentLikes commentLikesCommentUser = commentLikesRepository.findByCommentIdAndUserId(commentId,user.getId());
        if(commentLikesCommentUser == null){
            CommentLikes commentLikes = CommentLikes.builder()
                    .comment(comment)
                    .user(user)
                    .build();
            commentLikesRepository.save(commentLikes);
            return MessageResponseDto.builder()
                    .msg("댓글 좋아요 성공!")
                    .statusCode(HttpStatus.OK)
                    .build();
        }else {
            commentLikesRepository.delete(commentLikesCommentUser);
            return MessageResponseDto.builder()
                    .msg("댓글 좋아요 취소 성공!")
                    .statusCode(HttpStatus.OK)
                    .build();
        }
    }

}
