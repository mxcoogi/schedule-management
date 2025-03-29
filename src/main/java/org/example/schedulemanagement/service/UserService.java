package org.example.schedulemanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.schedulemanagement.dto.SignupRequestDto;
import org.example.schedulemanagement.dto.SignupResponseDto;
import org.example.schedulemanagement.entity.User;
import org.example.schedulemanagement.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    public SignupResponseDto signUp(SignupRequestDto requestDto) {

        try {
            User user = userRepository.findUserByEmailOrElseThrow(requestDto.getUserEmail());
            throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 존재하는 이메일입니다");
        } catch (RuntimeException e) {
            User user = new User(requestDto.getUserName(), requestDto.getUserEmail(), requestDto.getUserPassword());
            User savedUser = userRepository.save(user);
            return new SignupResponseDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail(), savedUser.getCreatedAt(), savedUser.getUpdatedAt());
        }
    }



}
