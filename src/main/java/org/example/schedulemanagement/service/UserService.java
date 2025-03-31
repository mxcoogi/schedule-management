package org.example.schedulemanagement.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.schedulemanagement.config.Const;
import org.example.schedulemanagement.dto.authdto.SavedSessionDto;
import org.example.schedulemanagement.dto.userdto.*;
import org.example.schedulemanagement.entity.User;
import org.example.schedulemanagement.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;


    @Transactional(readOnly = true)
    @Override
    public UserResponseDto findUser(Long userId) {

        User user = userRepository.findUserByIdOrElseThrow(userId);
        return new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getCreatedAt(), user.getUpdatedAt());
    }

    @Transactional
    @Override
    public UserResponseDto updateUser(UpdateRequestDto requestDto, HttpServletRequest httpServletRequest) {

        log.info("update user api 실행");
        SavedSessionDto savedSessionDto = (SavedSessionDto) httpServletRequest.getSession().getAttribute(Const.LOGIN_USER);
        User findUser = userRepository.findUserByIdOrElseThrow(savedSessionDto.getUserId());
        log.info("name : {}, updateAt : {}", findUser.getName(), findUser.getUpdatedAt());
        findUser.updateName(requestDto.getUpdateUserName());
        User updatedUser = userRepository.findUserByIdOrElseThrow(savedSessionDto.getUserId());
        log.info("name : {}, updateAt : {}", updatedUser.getName(), updatedUser.getUpdatedAt());
        return new UserResponseDto(updatedUser.getId(), updatedUser.getName(), updatedUser.getEmail(), updatedUser.getCreatedAt(), updatedUser.getUpdatedAt());
    }

    @Transactional
    @Override
    public void deleteUser(DeleteRequestDto requestDto,HttpServletRequest httpServletRequest) {
        SavedSessionDto savedSessionDto = (SavedSessionDto) httpServletRequest.getSession().getAttribute(Const.LOGIN_USER);
        User findUser = userRepository.findUserByIdOrElseThrow(savedSessionDto.getUserId());
        if (!findUser.getPassword().equals(requestDto.getUserPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        userRepository.delete(findUser);
    }


}
