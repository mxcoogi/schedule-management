package org.example.schedulemanagement.global.handler;


import lombok.extern.slf4j.Slf4j;
import org.example.schedulemanagement.dto.errordto.ErrorResponseDto;
import org.example.schedulemanagement.global.ErrorCode;
import org.example.schedulemanagement.global.exception.CustomeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHanlder {

    @ExceptionHandler(CustomeException.class)
    public ResponseEntity<ErrorResponseDto> customeException(CustomeException ex){
        return new ResponseEntity<>(new ErrorResponseDto(ex), ex.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        ErrorResponseDto dto = new ErrorResponseDto(errors.toString(), ErrorCode.INVALID_INPUT_VALUE.getHttpStatus(), ErrorCode.INVALID_INPUT_VALUE.getCode());

        return new ResponseEntity<>(dto, dto.getHttpStatus());
    }


}
