package org.example.schedulemanagement.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.schedulemanagement.dto.SignupRequestDto;
import org.example.schedulemanagement.dto.SignupResponseDto;
import org.example.schedulemanagement.dto.UserResponseDto;
import org.example.schedulemanagement.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final IUserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<SignupResponseDto> signUp(
            @RequestBody SignupRequestDto requestDto
            ){
        SignupResponseDto responseDto = userService.signUp(requestDto);
        return new ResponseEntity<>(responseDto,HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> findByUser(
            @PathVariable Long userId
    ){
        UserResponseDto responseDto = userService.findByUser(userId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }



}
