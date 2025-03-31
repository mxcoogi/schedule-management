package org.example.schedulemanagement.service;


import jakarta.servlet.http.HttpServletRequest;
import org.example.schedulemanagement.dto.authdto.LoginRequestDto;
import org.example.schedulemanagement.dto.authdto.SignUpRequestDto;
import org.example.schedulemanagement.dto.authdto.SignUpResponseDto;
import org.example.schedulemanagement.entity.User;

public interface IAuthService {

    SignUpResponseDto signUp(SignUpRequestDto requestDto);
    Long login(LoginRequestDto requestDto, HttpServletRequest request);

}
