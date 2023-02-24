package com.sparta.cleaningproject.exception;


import com.sparta.cleaningproject.dto.MessageResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

//RestController에서 발생한 예와나 에러를 감지하여 Handler로 처리하여  jsson 형식으로 반환

public class CustomExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<MessageResponseDto> customException(CustomException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        System.out.println("발생한 에러하는" + e + "case는" + e.getCause() + "이고 URI는" + requestURI + "입니다.");
        MessageResponseDto messageResponseDto = errorResponse(e.getException());
        return ResponseEntity.badRequest().body(messageResponseDto);
    }

    private MessageResponseDto errorResponse(Exception exception){
        return MessageResponseDto.builder()
                .statusCode(exception.getEcode())
                .msg(exception.getMsg())
                .build();
    }
}






//public class CustomExceptionHandler {
//    //Vaild를 통한 에러가 발생시 처리
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<MessageResponseDto> methodValidException(MethodArgumentNotValidException e, HttpServletRequest request){
//        MessageResponseDto messageResponseDto = errorResponse(e.getBindingResult());
//        return ResponseEntity.badRequest().body(messageResponseDto);
//    }
//    //CustomException을 통해 발생한 에러를 처리
//    @ExceptionHandler(CustomException.class)
//    public ResponseEntity<MessageResponseDto> customException(CustomException e){
//        MessageResponseDto messageResponseDto = errorResponse(e.getException());
//        return ResponseEntity.badRequest().body(messageResponseDto);
//    }
//    //Vaild결과로 발생한 예외를 반호나하기 위한 메소드
//    private MessageResponseDto errorResponse(BindingResult bindingResult){
//        String message="";
//
//        if(bindingResult.hasErrors()){
//            message = bindingResult.getAllErrors().get(0).getDefaultMessage();
//        }
//
//        return MessageResponseDto.builder()
//                .statusCode(HttpStatus.BAD_REQUEST)
//                .msg(message)
//                .build();
//    }
//
//    //CustomException Handling 결과를 반환할때 사용
//    private MessageResponseDto errorResponse(Exception exception){
//        return MessageResponseDto.builder()
//                .statusCode(exception.getEcode())
//                .msg(exception.getMsg())
//                .build();
//    }
////}
