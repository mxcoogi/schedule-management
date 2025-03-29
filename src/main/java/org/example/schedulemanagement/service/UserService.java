package org.example.schedulemanagement.service;


import org.example.schedulemanagement.dto.SignupRequestDto;
import org.example.schedulemanagement.dto.SignupResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    SignupResponseDto signUp(SignupRequestDto requestDto);

}
