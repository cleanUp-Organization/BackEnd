package com.sparta.cleaningproject.repository;

import com.sparta.cleaningproject.entity.BoardLikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardLikesRepository extends JpaRepository<BoardLikes,Long> {
    BoardLikes findByBoardIdAndUserId(Long BoardId, Long userId);
}
