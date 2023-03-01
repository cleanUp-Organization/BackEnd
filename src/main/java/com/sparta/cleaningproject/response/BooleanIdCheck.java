package com.sparta.cleaningproject.response;

import com.sparta.cleaningproject.entity.Board;
import com.sparta.cleaningproject.entity.User;
import com.sparta.cleaningproject.entity.UserRoleEnum;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Getter
@Component
public class BooleanIdCheck {
    public boolean CheckId(User user, Board board) {
        return Objects.equals(user.getId(), board.getUser().getId()) || user.getRole() == UserRoleEnum.ADMIN;
    }
}
