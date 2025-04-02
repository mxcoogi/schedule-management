package org.example.schedulemanagement.global.handler;


import org.example.schedulemanagement.dto.errordto.ErrorResponseDto;
import org.example.schedulemanagement.global.exception.CustomeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHanlder {

    @ExceptionHandler(CustomeException.class)
    public ResponseEntity<ErrorResponseDto> customeException(CustomeException ex){
        return new ResponseEntity<>(new ErrorResponseDto(ex), ex.getHttpStatus());
    }
}
