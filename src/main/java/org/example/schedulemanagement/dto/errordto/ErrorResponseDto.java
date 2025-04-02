package org.example.schedulemanagement.dto.errordto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.schedulemanagement.global.exception.MyException;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ErrorResponseDto {

    private String errorMessage;
    private HttpStatus httpStatus;
    private String errorCode;

    public ErrorResponseDto(MyException mx){
        this.errorMessage = mx.getMessage();
        this.httpStatus = mx.getHttpStatus();
        this.errorCode = mx.getCode();
    }

}
