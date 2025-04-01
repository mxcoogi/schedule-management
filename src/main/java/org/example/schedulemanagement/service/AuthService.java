package org.example.schedulemanagement.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.schedulemanagement.config.AuthConst;
import org.example.schedulemanagement.config.PasswordEncoder;
import org.example.schedulemanagement.dto.authdto.LoginRequestDto;
import org.example.schedulemanagement.dto.authdto.SavedSessionDto;
import org.example.schedulemanagement.dto.authdto.SignUpRequestDto;
import org.example.schedulemanagement.dto.authdto.SignUpResponseDto;
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
    private final PasswordEncoder passwordEncoder;


    @Override
    public SignUpResponseDto signUp(SignUpRequestDto requestDto) {

        Optional<User> findUser = userRepository.findUserByEmail(requestDto.getUserEmail());
        if(!findUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "존재하는 이메일");
        }
        String encodedPassword = passwordEncoder.encode(requestDto.getUserPassword());
        User user = new User(requestDto.getUserName(), requestDto.getUserEmail(), encodedPassword);
        User savedUser = userRepository.save(user);
        return new SignUpResponseDto(savedUser);
    }

    @Override
    public Long login(LoginRequestDto requestDto, HttpServletRequest request) {
        User loginUser = isValidEmailPassword(requestDto);
        HttpSession session = request.getSession();
        session.setAttribute(AuthConst.LOGIN_USER, new SavedSessionDto(loginUser));
        return loginUser.getId();
    }

    @Override
    public void logout(HttpServletRequest httpRequest) {
        HttpSession session = httpRequest.getSession(false);
        if(session != null) {
            session.invalidate();
        }
    }

    private User isValidEmailPassword(LoginRequestDto dto){
        User user = userRepository.findUserByEmailOrElseThrow(dto.getUserEmail());
//        if(!user.getEmail().equals(dto.getUserEmail())){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "이메일이 다릅니다");
//        }
        String rawPassword = dto.getUserPassword();
        if(!passwordEncoder.matchPassword(rawPassword, user.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 다릅니다");
        }
        return user;
    }



}
