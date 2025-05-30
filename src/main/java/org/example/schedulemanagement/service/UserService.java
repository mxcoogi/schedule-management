package org.example.schedulemanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.schedulemanagement.global.PasswordEncoder;
import org.example.schedulemanagement.dto.userdto.*;
import org.example.schedulemanagement.entity.User;
import org.example.schedulemanagement.global.ErrorCode;
import org.example.schedulemanagement.global.exception.CustomeException;
import org.example.schedulemanagement.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional(readOnly = true)
    @Override
    public UserResponseDto findUser(Long userId) {

        User user = userRepository.findUserByIdOrElseThrow(userId);
        return new UserResponseDto(user);
    }

    @Transactional
    @Override
    public UserResponseDto updateUser(UserUpdateRequestDto requestDto, Long userId) {

        log.info("update user api 실행");
        User findUser = userRepository.findUserByIdOrElseThrow(userId);
        log.info("name : {}, updateAt : {}", findUser.getName(), findUser.getUpdatedAt());
        findUser.updateName(requestDto.getUpdateUserName());
        User updatedUser = userRepository.findUserByIdOrElseThrow(userId);
        log.info("name : {}, updateAt : {}", updatedUser.getName(), updatedUser.getUpdatedAt());
        return new UserResponseDto(updatedUser);
    }

    @Transactional
    @Override
    public void deleteUser(DeleteRequestDto requestDto, Long userId) {
        User findUser = userRepository.findUserByIdOrElseThrow(userId);
        if (!passwordEncoder.matchPassword(requestDto.getUserPassword(), findUser.getPassword())) {
            throw new CustomeException(ErrorCode.FORBIDDEN);
        }
        userRepository.delete(findUser);
    }


}
