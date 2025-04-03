package org.example.schedulemanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.schedulemanagement.global.AuthConst;
import org.example.schedulemanagement.dto.userdto.*;
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


    @Operation(
            summary = "회원 조회",
            description = "회원 식별자로 회원 조회",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "완료"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "로그인을 해주세요"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "사용자를 찾을 수 없음"
                    )
            }
    )
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> findUser(
            @Positive @PathVariable Long userId
    ) {
        UserResponseDto responseDto = userService.findUser(userId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Operation(
            summary = "회원정보 수정",
            description = "회원정보를 수정합니다 (현재 이름만 업데이트)",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "업데이트 성공"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "잘못된 요청"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "로그인을 해주세요"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "사용자를 찾을 수 없음"
                    )
            }
    )
    @PutMapping
    public ResponseEntity<UserResponseDto> updateUser(
            @Valid @RequestBody UpdateRequestDto requestDto,
            HttpServletRequest httpRequest
    ) {
        Long userId = AuthConst.getUserId(httpRequest);
        UserResponseDto responseDto = userService.updateUser(requestDto,userId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Operation(
            summary = "회원 삭제",
            description = "비밀번호 확인후 회원 삭제합니다",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "삭제 성공"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "잘못된 요청"
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "비밀번호가 틀립니다 권한이 없습니다"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "사용자를 찾을 수 없음"
                    )
            }
    )
    @DeleteMapping
    public ResponseEntity<Void> deleteUser(
            @Valid @RequestBody DeleteRequestDto requestDto,
            HttpServletRequest httpRequest
    ) {
        Long userId = AuthConst.getUserId(httpRequest);
        userService.deleteUser(requestDto, userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
