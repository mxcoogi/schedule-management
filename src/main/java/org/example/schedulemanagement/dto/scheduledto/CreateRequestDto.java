package org.example.schedulemanagement.dto.scheduledto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class CreateRequestDto {
    @NotBlank
    @Length(max = 10)
    private final String scheduleTitle;
    @Length(max = 200)
    private final String scheduleContents;

    public CreateRequestDto(String scheduleTitle, String scheduleContents) {
        this.scheduleTitle = scheduleTitle;
        this.scheduleContents = scheduleContents;
    }
}
