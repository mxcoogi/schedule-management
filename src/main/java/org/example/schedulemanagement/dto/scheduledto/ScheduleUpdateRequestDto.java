package org.example.schedulemanagement.dto.scheduledto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class ScheduleUpdateRequestDto {


    @NotBlank
    @Length(max = 10)
    private final String updateScheduleTitle;
    @NotBlank
    @Length(max = 200)
    private final String updateScheduleContents;

    public ScheduleUpdateRequestDto(String updateScheduleTitle, String updateScheduleContents) {
        this.updateScheduleTitle = updateScheduleTitle;
        this.updateScheduleContents = updateScheduleContents;
    }
}
