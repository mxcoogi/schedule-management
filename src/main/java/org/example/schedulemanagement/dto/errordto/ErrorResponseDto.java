package org.example.schedulemanagement.dto.errordto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.schedulemanagement.global.ErrorCode;
import org.example.schedulemanagement.global.exception.MyException;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponseDto {

    private String errorMessage;
    private HttpStatus httpStatus;
    private int errorCode;

    public ErrorResponseDto(MyException mx){
        this.errorMessage = mx.getMessage();
        this.httpStatus = mx.getHttpStatus();
        this.errorCode = mx.getCode();
    }
    public ErrorResponseDto(String errorMessage, HttpStatus status, int errorCode){

        this.errorMessage = errorMessage;
        this.httpStatus = status;
        this.errorCode = errorCode;

    }

}
