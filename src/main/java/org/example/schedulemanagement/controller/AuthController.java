package org.example.schedulemanagement.controller;


import lombok.RequiredArgsConstructor;
import org.example.schedulemanagement.dto.authdto.SignUpRequestDto;
import org.example.schedulemanagement.dto.authdto.SignupResponseDto;
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
    public ResponseEntity<SignupResponseDto> signUp(
            @RequestBody SignUpRequestDto requestDto
    ){
        SignupResponseDto responseDto = authService.signUp(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
