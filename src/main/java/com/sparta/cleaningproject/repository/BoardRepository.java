package com.sparta.cleaningproject.repository;


import com.sparta.cleaningproject.entity.Board;
import com.sparta.cleaningproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {
    List<Board> findAllByOrderByCreatedAtDesc();
    void deleteAllByUser(User user);
}
