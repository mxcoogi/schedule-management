package org.example.schedulemanagement.dto.authdto;

import lombok.Getter;

@Getter
public class SavedSessionDto {
    private final Long userId;
    private final String userName;

    public SavedSessionDto(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
