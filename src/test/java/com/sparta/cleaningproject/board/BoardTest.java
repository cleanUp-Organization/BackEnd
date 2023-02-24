package com.sparta.cleaningproject.board;

import com.sparta.cleaningproject.controller.BoardController;
import com.sparta.cleaningproject.dto.BoardRequestDto;
import com.sparta.cleaningproject.entity.Board;
import com.sparta.cleaningproject.entity.User;
import com.sparta.cleaningproject.entity.UserRoleEnum;
import com.sparta.cleaningproject.repository.BoardRepository;
import com.sparta.cleaningproject.security.UserDetailsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class BoardTest {

    @InjectMocks
    private BoardController boardController;

    @Mock
    private BoardRepository boardRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("게시글 생성 테스트")
    public void createBoardTest() {
        // given
        BoardRequestDto requestDto = new BoardRequestDto();
        requestDto.setTitle("제목");
        requestDto.setContent("내용");
        requestDto.setImgUrl("asd");
        User user = User.builder()
                .password("asdasd")
                .role(UserRoleEnum.ADMIN)
                .username("hi")
                .build();
        Board board = Board.builder()
                .user(user)
                .boardRequestDto(requestDto)
                .build();

        when(boardRepository.save(any(Board.class))).thenReturn(board);
        // when

    }
}

