package org.example.schedulemanagement.service;


import org.example.schedulemanagement.dto.*;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {

    SignupResponseDto signUp(SignupRequestDto requestDto);
    UserResponseDto findByUser(Long userId);
    UserResponseDto updateUser(UpdateRequestDto requestDto);
    void deleteUser(DeleteRequestDto requestDto);

}
