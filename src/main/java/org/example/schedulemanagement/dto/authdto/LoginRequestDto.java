package org.example.schedulemanagement.dto.authdto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class LoginRequestDto {
    @Email
    private final String userEmail;
    @NotBlank
    @Length(min = 5)
    private final String userPassword;

    public LoginRequestDto(String userEmail, String userPassword) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }
}
