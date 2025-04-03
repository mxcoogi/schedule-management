package org.example.schedulemanagement.dto.userdto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class UserUpdateRequestDto {

    @Length(max = 4)
    @NotBlank
    private final String updateUserName;

    @JsonCreator
    public UserUpdateRequestDto(@JsonProperty("updateUserName") String updateUserName) {
        this.updateUserName = updateUserName;

    }
}
