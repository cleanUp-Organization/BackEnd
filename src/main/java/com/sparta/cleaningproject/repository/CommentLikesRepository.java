package com.sparta.cleaningproject.repository;

import com.sparta.cleaningproject.entity.CommentLikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikesRepository extends JpaRepository<CommentLikes,Long> {
    CommentLikes findByCommentIdAndUserId(Long commentId, Long userId);
}
