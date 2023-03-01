package com.sparta.cleaningproject.response;

import lombok.Getter;

@Getter
public enum ResponseMsg {
    CONSTRUCT_SUCCESS("생성 성공"),
    BOARD_UPDATE_SUCCESS("게시글 수정 성공"),
    BOARD_DELETE_SUCCESS("게시글 삭제 성공"),
    COMMENT_CREATE_SUCCESS("댓글 작성 성공"),
    COMMENT_UPDATE_SUCCESS("댓글 수정 성공"),
    COMMENT_DELETE_SUCCESS("댓글 삭제 성공"),
    BOARD_LIKE_SUCCESS("게시글 좋아요 성공"),
    BOARD_UNLIKE_SUCCESS("게시글 좋아요 취소 성공"),
    COMMENT_LIKE_SUCCESS("댓글 좋아요 성공"),
    COMMENT_UNLIKE_SUCCESS("댓글 좋아요 취소 성공"),
    SIGNUP_SUCCESS("정상적으로 회원가입이 완료되었습니다"),
    LOGIN_SUCCESS("로그인 성공"),
    WITHDRAWAL_SUCCESS("회원 탈퇴 성공");
    private String msg;

    ResponseMsg(String msg) {
        this.msg = msg;
    }
}
