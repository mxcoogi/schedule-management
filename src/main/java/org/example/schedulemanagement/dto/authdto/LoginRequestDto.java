package org.example.schedulemanagement.dto.authdto;

import lombok.Getter;

@Getter
public class LoginRequestDto {
    private final String userEmail;
    private final String userPassword;

    public LoginRequestDto(String userEmail, String userPassword) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }
}
