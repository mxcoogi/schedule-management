package org.example.schedulemanagement.dto.userdto;

import lombok.Getter;
import org.example.schedulemanagement.entity.User;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {

    private final Long userId;

    private final String userName;

    private final String userEmail;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public UserResponseDto(Long userId, String userName, String userEmail, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UserResponseDto(User user){
        this.userId = user.getId();
        this.userName = user.getName();
        this.userEmail = user.getEmail();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }
}
