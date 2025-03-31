package org.example.schedulemanagement.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.schedulemanagement.config.Const;
import org.example.schedulemanagement.dto.userdto.*;
import org.example.schedulemanagement.filter.AuthFilter;
import org.example.schedulemanagement.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final IUserService userService;


    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> findUser(
            @PathVariable Long userId
    ) {
        UserResponseDto responseDto = userService.findUser(userId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserResponseDto> updateUser(
            @RequestBody UpdateRequestDto requestDto,
            HttpServletRequest httpRequest
    ) {
        Long userId = AuthFilter.getUserId(httpRequest);
        UserResponseDto responseDto = userService.updateUser(requestDto,userId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(
            @RequestBody DeleteRequestDto requestDto,
            HttpServletRequest httpRequest
    ) {
        Long userId = AuthFilter.getUserId(httpRequest);
        userService.deleteUser(requestDto, userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
