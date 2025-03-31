package org.example.schedulemanagement.dto.scheduledto;

import lombok.Getter;

@Getter
public class CreateRequestDto {
    private final String scheduleName;
    private final String scheduleContents;
    private final String userEmail;
    private final String userPassword;

    public CreateRequestDto(String scheduleName, String scheduleContents, String userEmail, String userPassword) {
        this.scheduleName = scheduleName;
        this.scheduleContents = scheduleContents;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }
}
