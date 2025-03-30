package org.example.schedulemanagement.dto;

import lombok.Getter;

@Getter
public class UpdateRequestDto {

    private final String updateUserName;
    private final String userEmail;
    private final String userPassword;

    public UpdateRequestDto(String updateUserName, String userEmail, String userPassword) {
        this.updateUserName = updateUserName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }
}
