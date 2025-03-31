package org.example.schedulemanagement.dto.authdto;

import lombok.Getter;

@Getter
public class SignUpRequestDto {

    private final String userName;

    private final String userEmail;

    private final String userPassword;

    public SignUpRequestDto(String userName, String userEmail, String userPassword) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }
}
