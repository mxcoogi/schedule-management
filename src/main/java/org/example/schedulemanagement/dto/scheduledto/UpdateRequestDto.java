package org.example.schedulemanagement.dto.scheduledto;

import lombok.Getter;

@Getter
public class UpdateRequestDto {


    private final String updateScheduleTitle;
    private final String updateScheduleContents;

    public UpdateRequestDto(String updateScheduleTitle, String updateScheduleContents) {
        this.updateScheduleTitle = updateScheduleTitle;
        this.updateScheduleContents = updateScheduleContents;
    }
}
