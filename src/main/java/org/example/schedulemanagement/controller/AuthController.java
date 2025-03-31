package org.example.schedulemanagement.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.schedulemanagement.dto.authdto.LoginRequestDto;
import org.example.schedulemanagement.dto.authdto.SignUpRequestDto;
import org.example.schedulemanagement.dto.authdto.SignUpResponseDto;
import org.example.schedulemanagement.service.IAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResponseDto> signUp(
            @RequestBody SignUpRequestDto requestDto
    ){
        SignUpResponseDto responseDto = authService.signUp(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Long> login(
            @RequestBody LoginRequestDto requestDto,
            HttpServletRequest request
            ){
        Long userId = authService.login(requestDto, request);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

}
