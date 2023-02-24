package com.sparta.cleaningproject.service;

import com.sparta.cleaningproject.dto.CommentRequestDto;
import com.sparta.cleaningproject.dto.CommentResponseDto;
import com.sparta.cleaningproject.dto.MessageResponseDto;
import com.sparta.cleaningproject.entity.Board;
import com.sparta.cleaningproject.entity.Comment;
import com.sparta.cleaningproject.entity.User;
import com.sparta.cleaningproject.entity.UserRoleEnum;
import com.sparta.cleaningproject.exception.CustomException;
import com.sparta.cleaningproject.repository.BoardRepository;
import com.sparta.cleaningproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.sparta.cleaningproject.exception.Exception.*;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public CommentResponseDto createComment(Long id, CommentRequestDto requestDto, User user){

        Board board = boardRepository.findById(id).orElseThrow(
                () -> new CustomException(NOT_FOUND_BOARD)
        );
        Comment comment =  commentRepository.save(Comment.builder()
                .contents(requestDto.getContents())
                .board(board)
                .user(user).build());;
        return new CommentResponseDto(comment);
    }

    public CommentResponseDto updateComment(Long id, CommentRequestDto requestDto , User user){

        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new CustomException(NOT_FOUND_COMMENT)
        );
        Optional<Comment> found = commentRepository.findByIdAndUser(id,user);
        if(found.isEmpty() && user.getRole() == UserRoleEnum.USER){
            throw new CustomException(AUTHORIZATION);
        }
        comment.update(requestDto.getContents(),user);
        return new CommentResponseDto(comment);
    }

    public MessageResponseDto deleteComment(Long id, User user) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new CustomException(NOT_FOUND_COMMENT)
        );
        Optional<Comment> found = commentRepository.findByIdAndUser(id, user);
        if(found.isEmpty() && user.getRole() == UserRoleEnum.USER){
            throw new CustomException(AUTHORIZATION);
        }
        commentRepository.deleteById(id);
        return MessageResponseDto.builder()
                .msg("댓글 삭제 완료")
                .statusCode(HttpStatus.OK)
                .build();
    }
}
