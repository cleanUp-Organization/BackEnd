package com.sparta.cleaningproject.entity;

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

    @ManyToOne
    @JoinColumn(name = "USERS_ID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "BOARD_ID", nullable = false)
    private Board board;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private List<Likes> likesList = new ArrayList<>();
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

}
