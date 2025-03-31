package org.example.schedulemanagement.dto.scheduledto;

import lombok.Getter;

@Getter
public class CreateRequestDto {
    private final String scheduleTitle;
    private final String scheduleContents;

    public CreateRequestDto(String scheduleTitle, String scheduleContents) {
        this.scheduleTitle = scheduleTitle;
        this.scheduleContents = scheduleContents;
    }
}
