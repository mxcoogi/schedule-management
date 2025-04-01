package org.example.schedulemanagement.dto.errordto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponseDto {

    private int errorCode;
    private String errorMessage;

}
