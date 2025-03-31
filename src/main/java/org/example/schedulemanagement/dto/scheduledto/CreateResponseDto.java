package org.example.schedulemanagement.dto.scheduledto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateResponseDto {
    private final Long scheduleId;
    private final String scheduleName;
    private final String scheduleContents;
    private final String userName;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public CreateResponseDto(Long scheduleId, String scheduleName, String scheduleContents, String userName, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.scheduleId = scheduleId;
        this.scheduleName = scheduleName;
        this.scheduleContents = scheduleContents;
        this.userName = userName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
