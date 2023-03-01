package com.sparta.cleaningproject.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
    @Getter
    @Entity
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public class CommentLikes {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "USERS_ID", nullable = false)
        private User user;

        @ManyToOne
        @JoinColumn(name = "COMMENT_ID")
        private Comment comment;

        @Builder
        public CommentLikes(User user, Comment comment) {
            this.user = user;
            this.comment = comment;
        }

        public static CommentLikes of(Comment comment, User user) {
            return CommentLikes.builder()
                    .comment(comment)
                    .user(user)
                    .build();
        }
    }

