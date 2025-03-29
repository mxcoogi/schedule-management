package org.example.schedulemanagement.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.schedulemanagement.dto.SignupRequestDto;
import org.example.schedulemanagement.dto.SignupResponseDto;
import org.example.schedulemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<SignupResponseDto> signUp(
            @RequestBody SignupRequestDto requestDto
            ){

        SignupResponseDto responseDto = userService.signUp(requestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
