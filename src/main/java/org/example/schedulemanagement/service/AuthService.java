package org.example.schedulemanagement.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulemanagement.dto.authdto.SignUpRequestDto;
import org.example.schedulemanagement.dto.userdto.SignupRequestDto;
import org.example.schedulemanagement.entity.User;
import org.example.schedulemanagement.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService{

    private final UserRepository userRepository;


    @Override
    public Long signUp(SignUpRequestDto requestDto) {

        Optional<User> findUser = userRepository.findUserByEmail(requestDto.getUserEmail());
        if(!findUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "존재하는 이메일");
        }
        User user = new User(requestDto.getUserName(), requestDto.getUserEmail(), requestDto.getUserPassword());
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }


}
