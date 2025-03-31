package org.example.schedulemanagement.service;


import org.example.schedulemanagement.dto.authdto.SignUpRequestDto;

public interface IAuthService {

    Long signUp(SignUpRequestDto requestDto);

}
