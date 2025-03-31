package org.example.schedulemanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @Transactional
    @Override
    public SignupResponseDto signUp(SignupRequestDto requestDto) {

        Optional<User> findUser = userRepository.findUserByEmail(requestDto.getUserEmail());
        if(!findUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "존재하는 이메일");
        }
        User user = new User(requestDto.getUserName(), requestDto.getUserEmail(), requestDto.getUserPassword());
        User savedUser = userRepository.save(user);
        return new SignupResponseDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail(), savedUser.getCreatedAt(), savedUser.getUpdatedAt());

    }

    @Transactional(readOnly = true)
    @Override
    public UserResponseDto findUser(Long userId) {

        User user = userRepository.findUserByIdOrElseThrow(userId);
        return new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getCreatedAt(), user.getUpdatedAt());
    }

    @Transactional
    @Override
    public UserResponseDto updateUser(UpdateRequestDto requestDto) {
        User findUser = userRepository.findUserByEmailOrElseThrow(requestDto.getUserEmail());
        if (!findUser.getPassword().equals(requestDto.getUserPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        log.info("name : {}, updateAt : {}", findUser.getName(), findUser.getUpdatedAt());
        findUser.updateName(requestDto.getUpdateUserName());
        User updatedUser = userRepository.findUserByEmailOrElseThrow(requestDto.getUserEmail());
        log.info("name : {}, updateAt : {}", updatedUser.getName(), updatedUser.getUpdatedAt());
        return new UserResponseDto(updatedUser.getId(), updatedUser.getName(), updatedUser.getEmail(), updatedUser.getCreatedAt(), updatedUser.getUpdatedAt());
    }

    @Transactional
    @Override
    public void deleteUser(DeleteRequestDto requestDto) {
        User findUser = userRepository.findUserByEmailOrElseThrow(requestDto.getUserEmail());
        if (!findUser.getPassword().equals(requestDto.getUserPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        userRepository.delete(findUser);
    }


}
