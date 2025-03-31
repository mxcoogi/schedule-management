package org.example.schedulemanagement.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class AuthenticationException extends RuntimeException {
    private final static String MESSAGE = "Authentication failed. Please check your credentials.";
    public AuthenticationException() {
        super(MESSAGE);
    }
}
