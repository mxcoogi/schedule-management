package org.example.schedulemanagement.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.schedulemanagement.config.Const;
import org.example.schedulemanagement.dto.authdto.LoginRequestDto;
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


    @Override
    public SignUpResponseDto signUp(SignUpRequestDto requestDto) {

        Optional<User> findUser = userRepository.findUserByEmail(requestDto.getUserEmail());
        if(!findUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "존재하는 이메일");
        }
        User user = new User(requestDto.getUserName(), requestDto.getUserEmail(), requestDto.getUserPassword());
        User savedUser = userRepository.save(user);
        return new SignUpResponseDto(savedUser.getId(),savedUser.getName(), savedUser.getEmail(), savedUser.getCreatedAt(), savedUser.getUpdatedAt());
    }

    @Override
    public Long login(LoginRequestDto requestDto, HttpServletRequest request) {
        User loginUser = isValidEmailPassword(requestDto);
        HttpSession session = request.getSession();
        session.setAttribute(Const.LOGIN_USER, loginUser);
        return loginUser.getId();
    }

    @Override
    public void logout(HttpServletRequest httpRequest) {
        HttpSession session = httpRequest.getSession(false);
        // 세션이 존재하면 -> 로그인이 된 경우
        if(session != null) {
            session.invalidate(); // 해당 세션(데이터)을 삭제한다.
        }
    }

    private User isValidEmailPassword(LoginRequestDto dto){
        User user = userRepository.findUserByEmailOrElseThrow(dto.getUserEmail());
//        if(!user.getEmail().equals(dto.getUserEmail())){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "이메일이 다릅니다");
//        }
        if(!user.getPassword().equals(dto.getUserPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 다릅니다");
        }
        return user;
    }


}
