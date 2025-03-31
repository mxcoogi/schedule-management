package org.example.schedulemanagement.dto.userdto;

import lombok.Getter;

@Getter
public class UpdateRequestDto {

    private final String updateUserName;

    public UpdateRequestDto(String updateUserName) {
        this.updateUserName = updateUserName;

    }
}
