package com.sparta.cleaningproject.service;

import com.sparta.cleaningproject.dto.CommentRequestDto;
import com.sparta.cleaningproject.dto.MessageResponseDto;
import com.sparta.cleaningproject.entity.Board;
import com.sparta.cleaningproject.entity.Comment;
import com.sparta.cleaningproject.entity.User;
import com.sparta.cleaningproject.entity.UserRoleEnum;
import com.sparta.cleaningproject.exception.CustomException;
import com.sparta.cleaningproject.repository.BoardRepository;
import com.sparta.cleaningproject.repository.CommentRepository;
import com.sparta.cleaningproject.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.sparta.cleaningproject.exception.Exception.*;
import static com.sparta.cleaningproject.response.ResponseMsg.*;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final ApiResponse apiResponse;

    @Transactional
    public MessageResponseDto createComment(Long boardId, CommentRequestDto requestDto, User user){

        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new CustomException(NOT_FOUND_BOARD)
        );
        Comment comment =  commentRepository.save(Comment.builder()
                .contents(requestDto.getContents())
                .board(board)
                .user(user).build());
                comment.setBoard(board);
        return apiResponse.success(COMMENT_CREATE_SUCCESS.getMsg());
    }
    @Transactional
    public MessageResponseDto updateComment(Long commentId, CommentRequestDto requestDto , User user){

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new CustomException(NOT_FOUND_COMMENT)
        );
        Optional<Comment> found = commentRepository.findByIdAndUser(commentId,user);
        if(found.isEmpty() && user.getRole() == UserRoleEnum.USER){
            throw new CustomException(AUTHORIZATION);
        }
        comment.update(requestDto.getContents(),user);
        return apiResponse.success(COMMENT_UPDATE_SUCCESS.getMsg());
    }
    @Transactional
    public MessageResponseDto deleteComment(Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new CustomException(NOT_FOUND_COMMENT)
        );
        Optional<Comment> found = commentRepository.findByIdAndUser(commentId, user);
        if(found.isEmpty() && user.getRole() == UserRoleEnum.USER){
            throw new CustomException(AUTHORIZATION);
        }
        commentRepository.deleteById(commentId);
        return apiResponse.success(COMMENT_DELETE_SUCCESS.getMsg());
    }
}
