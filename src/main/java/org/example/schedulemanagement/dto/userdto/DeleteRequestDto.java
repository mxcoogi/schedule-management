package org.example.schedulemanagement.dto.userdto;

import lombok.Getter;

@Getter
public class DeleteRequestDto {

    private final String userPassword;

    public DeleteRequestDto(String userPassword) {
        this.userPassword = userPassword;
    }
}
