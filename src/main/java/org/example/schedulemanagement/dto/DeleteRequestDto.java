package org.example.schedulemanagement.dto;

import lombok.Getter;

@Getter
public class DeleteRequestDto {

    private final String userEmail;
    private final String userPassword;

    public DeleteRequestDto(String userEmail, String userPassword) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }
}
