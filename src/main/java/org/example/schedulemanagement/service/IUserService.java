package org.example.schedulemanagement.service;


import jakarta.servlet.http.HttpServletRequest;
import org.example.schedulemanagement.dto.userdto.*;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {

    UserResponseDto findUser(Long userId);
    UserResponseDto updateUser(UpdateRequestDto requestDto, HttpServletRequest httpServletRequest);
    void deleteUser(DeleteRequestDto requestDto);

}
