package org.example.schedulemanagement.dto.authdto;

import lombok.Getter;
import org.example.schedulemanagement.entity.User;

@Getter
public class SavedSessionDto {
    private final Long userId;
    private final String userName;

    public SavedSessionDto(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public SavedSessionDto(User user){
        this.userId = user.getId();
        this.userName = user.getName();
    }
}
