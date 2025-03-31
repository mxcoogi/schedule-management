package org.example.schedulemanagement.service;


import org.example.schedulemanagement.dto.userdto.*;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {

    UserResponseDto findUser(Long userId);
    UserResponseDto updateUser(UpdateRequestDto requestDto);
    void deleteUser(DeleteRequestDto requestDto);

}
