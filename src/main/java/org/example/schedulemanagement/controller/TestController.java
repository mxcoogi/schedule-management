package org.example.schedulemanagement.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Tag(name = "테스트", description = "테스트 관련 API")
public class TestController {

    @Operation(
            summary = "테스트메서드",
            description = "테스트메서드입니다",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "테스트 성공"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "서버 에러"
                    )
            }
    )
    @GetMapping
    public String test(){
        return "test";
    }
}
