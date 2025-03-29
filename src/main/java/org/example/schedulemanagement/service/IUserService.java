package org.example.schedulemanagement.service;


import org.example.schedulemanagement.dto.SignupRequestDto;
import org.example.schedulemanagement.dto.SignupResponseDto;
import org.example.schedulemanagement.dto.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {

    SignupResponseDto signUp(SignupRequestDto requestDto);
    UserResponseDto findByUser(Long userId);

}
