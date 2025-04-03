package org.example.schedulemanagement.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
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

    @Operation(
            summary = "회원가입",
            description = "이메일, 비밀번호, 이름을 등록해 회원가입 합니다",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "회원가입 성공"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "잘못된 요청"
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "이미 존재하는 이메일"
                    )
            }
    )
    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResponseDto> signUp(
            @Valid @RequestBody SignUpRequestDto requestDto
    ) {
        SignUpResponseDto responseDto = authService.signUp(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @Operation(
            summary = "로그인",
            description = "이메일, 비밀번호로 로그인 합니다",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "로그인 성공"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "잘못된 요청"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "이메일과 비밀번호가 맞지 않음"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "사용자를 찾을 수 없음"
                    )
            }
    )
    @PostMapping("/login")
    public ResponseEntity<Long> login(
            @Valid @RequestBody LoginRequestDto requestDto,
            HttpServletRequest httpRequest
    ) {
        Long userId = authService.login(requestDto, httpRequest);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    @Operation(
            summary = "로그아웃",
            description = "이메일, 비밀번호로 로그인 합니다",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "로그아웃 성공"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "로그인해주세요"
                    )
            }
    )
    @PostMapping("/logout")
    public ResponseEntity<Void> login(
            HttpServletRequest httpRequest
    ) {
        authService.logout(httpRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
