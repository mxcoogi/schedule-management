package org.example.schedulemanagement.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MyException extends RuntimeException{

    private final HttpStatus httpStatus;
    private final String code;
    MyException(String message, HttpStatus httpStatus, String code){
        super(message);
        this.httpStatus = httpStatus;
        this.code = code;
    }

}
