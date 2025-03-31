package org.example.schedulemanagement.dto.authdto;

import lombok.Getter;
import org.example.schedulemanagement.entity.User;

import java.time.LocalDateTime;

@Getter
public class SignUpResponseDto {
    private final Long userId;
    private final String userName;
    private final String userEmail;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public SignUpResponseDto(User savedUser) {
        this.userId = savedUser.getId();
        this.userName = savedUser.getName();
        this.userEmail = savedUser.getEmail();
        this.createdAt = savedUser.getCreatedAt();
        this.updatedAt = savedUser.getUpdatedAt();
    }
}
