package org.example.schedulemanagement.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MyException extends RuntimeException{
    private String MESSAGE;
    private HttpStatus httpStatus;
}
