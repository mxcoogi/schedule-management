package org.example.schedulemanagement.dto.scheduledto;

import lombok.Getter;
import org.example.schedulemanagement.dto.commentdto.CommentResponseDto;
import org.example.schedulemanagement.entity.Comment;
import org.example.schedulemanagement.entity.Schedule;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ScheduleResponseDto {
    private final Long scheduleId;
    private final String scheduleTitle;
    private final String scheduleContents;
    private final String userName;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final List<CommentResponseDto> commentList;


    public ScheduleResponseDto(Schedule schedule){
        this.scheduleId = schedule.getId();
        this.scheduleTitle = schedule.getTitle();
        this.scheduleContents = schedule.getContents();
        this.userName = schedule.getUser().getName();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
        this.commentList = null;
    }

    public ScheduleResponseDto(Schedule schedule, List<CommentResponseDto> commentList){
        this.scheduleId = schedule.getId();
        this.scheduleTitle = schedule.getTitle();
        this.scheduleContents = schedule.getContents();
        this.userName = schedule.getUser().getName();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
        this.commentList = commentList;
    }
}
