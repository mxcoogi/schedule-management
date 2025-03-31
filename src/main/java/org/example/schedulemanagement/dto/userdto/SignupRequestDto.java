package org.example.schedulemanagement.dto.userdto;

import lombok.Getter;

@Getter
public class SignupRequestDto {

    private final String userName;

    private final String userEmail;

    private final String userPassword;

    public SignupRequestDto(String userName, String userEmail, String userPassword) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }
}
