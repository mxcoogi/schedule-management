package org.example.schedulemanagement.handler;


import org.example.schedulemanagement.exception.UnAuthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHanlder {

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<String> authenticationException(UnAuthorizedException ex){

        return new ResponseEntity<>(ex.getMessage(), ex.getHttpStatus());
    }
}
