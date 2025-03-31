package org.example.schedulemanagement.dto.scheduledto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateResponseDto {
    private final Long scheduleId;
    private final String scheduleTitle;
    private final String scheduleContents;
    private final String userName;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public CreateResponseDto(Long scheduleId, String scheduleTitle, String scheduleContents, String userName, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.scheduleId = scheduleId;
        this.scheduleTitle = scheduleTitle;
        this.scheduleContents = scheduleContents;
        this.userName = userName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
