package org.example.schedulemanagement.dto.userdto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class DeleteRequestDto {

    @NotBlank
    @Length(min = 5)
    private final String userPassword;

    public DeleteRequestDto(String userPassword) {
        this.userPassword = userPassword;
    }
}
