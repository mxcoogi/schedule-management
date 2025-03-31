package org.example.schedulemanagement.dto.scheduledto;

import lombok.Getter;
import org.example.schedulemanagement.entity.Schedule;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private final Long scheduleId;
    private final String scheduleTitle;
    private final String scheduleContents;
    private final String userName;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ScheduleResponseDto(Long scheduleId, String scheduleTitle, String scheduleContents, String userName, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.scheduleId = scheduleId;
        this.scheduleTitle = scheduleTitle;
        this.scheduleContents = scheduleContents;
        this.userName = userName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ScheduleResponseDto(Schedule schedule){
        this.scheduleId = schedule.getId();
        this.scheduleTitle = schedule.getTitle();
        this.scheduleContents = schedule.getContents();
        this.userName = schedule.getUser().getName();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
    }
}
