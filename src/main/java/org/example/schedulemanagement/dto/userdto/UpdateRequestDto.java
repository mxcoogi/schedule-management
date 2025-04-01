package org.example.schedulemanagement.dto.userdto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class UpdateRequestDto {

    @Length(max = 4)
    @NotBlank
    private final String updateUserName;

    public UpdateRequestDto(String updateUserName) {
        this.updateUserName = updateUserName;

    }
}
