package org.example.schedulemanagement.handler;

import org.example.schedulemanagement.exception.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ScheduleHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> authenticationException(AuthenticationException ex){

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
