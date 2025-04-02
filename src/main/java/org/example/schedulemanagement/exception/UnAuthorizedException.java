package org.example.schedulemanagement.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class UnAuthorizedException extends MyException{
    private final String message = "로그인이 필요합니다.";
    private final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
}

