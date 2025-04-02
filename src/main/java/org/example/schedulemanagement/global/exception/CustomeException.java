package org.example.schedulemanagement.global.exception;

import lombok.Getter;
import org.example.schedulemanagement.global.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
public class CustomeException extends MyException{


    public CustomeException(ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode.getHttpStatus(), errorCode.getCode());
    }
}

