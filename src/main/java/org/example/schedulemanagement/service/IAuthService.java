package org.example.schedulemanagement.service;


import org.example.schedulemanagement.dto.authdto.SignUpRequestDto;
import org.example.schedulemanagement.dto.authdto.SignupResponseDto;

public interface IAuthService {

    SignupResponseDto signUp(SignUpRequestDto requestDto);

}
