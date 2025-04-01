package org.example.schedulemanagement.dto.authdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class SignUpRequestDto {

    @NotBlank
    @Length(max = 4)
    private final String userName;

    @Email
    private final String userEmail;

    @NotBlank
    @Length(min = 5)
    private final String userPassword;

    public SignUpRequestDto(String userName, String userEmail, String userPassword) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }
}
