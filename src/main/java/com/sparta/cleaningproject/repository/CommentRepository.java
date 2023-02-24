package com.sparta.cleaningproject.repository;

import com.sparta.cleaningproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
