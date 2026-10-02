package com.example.socialmedia.exception;

import com.example.socialmedia.dto.ErrorResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
   
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException e) {
        return getErrorResponseResponseEntity(e.getMessage(), 404);
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUsernameAlreadyExists(UsernameAlreadyExistsException e){
        return getErrorResponseResponseEntity(e.getMessage(), 409);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception e){
        log.debug(e.getMessage());
        System.out.println(e.getMessage());
        return getErrorResponseResponseEntity("Something went wrong", 500);
    }

    private static ResponseEntity<ErrorResponse> getErrorResponseResponseEntity(String message, Integer status) {
        return ResponseEntity.status(status).body(ErrorResponse.builder()
                .error(message)
                .status(status)
                .build());
    }

}


