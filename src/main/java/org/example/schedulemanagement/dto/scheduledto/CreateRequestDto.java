package org.example.schedulemanagement.dto.scheduledto;

import lombok.Getter;

@Getter
public class CreateRequestDto {
    private final String scheduleTitle;
    private final String scheduleContents;
    private final String userEmail;
    private final String userPassword;

    public CreateRequestDto(String scheduleTitle, String scheduleContents, String userEmail, String userPassword) {
        this.scheduleTitle = scheduleTitle;
        this.scheduleContents = scheduleContents;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }
}
