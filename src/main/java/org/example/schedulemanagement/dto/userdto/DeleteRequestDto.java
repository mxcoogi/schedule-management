package org.example.schedulemanagement.dto.userdto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class DeleteRequestDto {

    @NotBlank
    @Length(min = 5)
    private final String userPassword;

    @JsonCreator
    public DeleteRequestDto(@JsonProperty("userPassword") String userPassword) {
        this.userPassword = userPassword;
    }
}
