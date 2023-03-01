package com.sparta.cleaningproject.service;

import com.sparta.cleaningproject.dto.MessageResponseDto;
import com.sparta.cleaningproject.entity.*;
import com.sparta.cleaningproject.exception.CustomException;
import com.sparta.cleaningproject.exception.Exception;
import com.sparta.cleaningproject.repository.BoardLikesRepository;
import com.sparta.cleaningproject.repository.BoardRepository;
import com.sparta.cleaningproject.repository.CommentLikesRepository;
import com.sparta.cleaningproject.repository.CommentRepository;
import com.sparta.cleaningproject.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.sparta.cleaningproject.response.ResponseMsg.*;

@Service
@RequiredArgsConstructor
public class LikesService {
    private final BoardRepository boardRepository;
    private final BoardLikesRepository boardLikesRepository;
    private final CommentRepository commentRepository;
    private final CommentLikesRepository commentLikesRepository;
    private final ApiResponse apiResponse;

    @Transactional
    public MessageResponseDto boardLike(User user, Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new CustomException(Exception.NOT_FOUND_BOARD)
        );
        BoardLikes boardLikesBoardUser = boardLikesRepository.findByBoardIdAndUserId(boardId,user.getId());
        if (boardLikesBoardUser == null) {
            BoardLikes boardLikes = BoardLikes.of(board, user);
            boardLikesRepository.save(boardLikes);
            return apiResponse.success(BOARD_LIKE_SUCCESS.getMsg());
        } else {
            boardLikesRepository.delete(boardLikesBoardUser);
            return apiResponse.success(BOARD_UNLIKE_SUCCESS.getMsg());
        }
    }
    @Transactional
    public MessageResponseDto commentLike(User user , Long commentId){
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new CustomException(Exception.NOT_FOUND_COMMENT)
        );
        CommentLikes commentLikesCommentUser = commentLikesRepository.findByCommentIdAndUserId(commentId,user.getId());
        if(commentLikesCommentUser == null){
            CommentLikes commentLikes = CommentLikes.of(comment, user);
            commentLikesRepository.save(commentLikes);
            return apiResponse.success(COMMENT_LIKE_SUCCESS.getMsg());
        }else {
            commentLikesRepository.delete(commentLikesCommentUser);
            return apiResponse.success(COMMENT_UNLIKE_SUCCESS.getMsg());
        }
    }

}
