package com.sparta.cleaningproject.entity;

import com.sparta.cleaningproject.dto.CommentRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERS_ID", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID", nullable = false)
    private Board board;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private List<CommentLikes> commentLikes = new ArrayList<>();

    //연관관계 편의 메서드
    public void setBoard(Board board) {
        if (this.board != null) {
            this.board.getCommentList().remove(this);
        }
        this.board = board;
        board.getCommentList().add(this);
    }

    @Builder
    public Comment(String contents, Board board, User user){
        this.contents = contents;
        this.board = board;
        this.user = user;
    }

    public void update(String contents, User user) {
        this.contents = contents;
        this.user = user;
    }

    public static Comment of(String contents, Board board, User user){
        return builder()
                .contents(contents)
                .board(board)
                .user(user).build();
    }

}
