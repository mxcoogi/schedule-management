package org.example.schedulemanagement.dto.scheduledto;

import lombok.Getter;

@Getter
public class UpdateRequestDto {


    private final String updateScheduleTitle;
    private final String updateScheduleContents;
    private final String userEmail;
    private final String userPassword;

    public UpdateRequestDto(String updateScheduleTitle, String updateScheduleContents, String userEmail, String userPassword) {
        this.updateScheduleTitle = updateScheduleTitle;
        this.updateScheduleContents = updateScheduleContents;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }
}
