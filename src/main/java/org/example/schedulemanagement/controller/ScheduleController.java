package org.example.schedulemanagement.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.example.schedulemanagement.global.AuthConst;
import org.example.schedulemanagement.dto.scheduledto.CreateRequestDto;
import org.example.schedulemanagement.dto.scheduledto.ScheduleAllResponseDto;
import org.example.schedulemanagement.dto.scheduledto.ScheduleResponseDto;
import org.example.schedulemanagement.dto.scheduledto.UpdateRequestDto;
import org.example.schedulemanagement.service.IScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final IScheduleService scheduleService;


    @Operation(
            summary = "일정 생성",
            description = "일정을 생성 합니다",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "일정 생성 성공"
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
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(
            @Valid @RequestBody CreateRequestDto requestDto,
            HttpServletRequest httpRequest
    ) {
        Long userId= AuthConst.getUserId(httpRequest);
        ScheduleResponseDto responseDto = scheduleService.createSchedule(requestDto, userId);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @Operation(
            summary = "일정 단건 조회",
            description = "일정 식별자로 단일 일정 조회",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "조회 성공"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "로그인 필요"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "일정을 찾을 수 없음"
                    )
            }
    )
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> findSchedule(
            @Positive @PathVariable Long scheduleId
    ) {
        ScheduleResponseDto schedule = scheduleService.findSchedule(scheduleId);
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

//    @GetMapping()
//    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedule(
//            HttpServletRequest httpRequest
//    ) {
//        Long userId = HttpGetRequest.getUserId(httpRequest);
//        List<ScheduleResponseDto> responseDtoList = scheduleService.findAllSchedule(userId);
//        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
//    }

    @Operation(
            summary = "일정 전체 조회",
            description = "일정을 전체 조회합니다",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "조회 성공"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "잘못된 요청"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "로그인이 필요함"
                    )
            }
    )
    @GetMapping
    public ResponseEntity<ScheduleAllResponseDto> findAllSchedule(
            @Positive @RequestParam(value = "page", defaultValue = "1") int page,
            @Positive @RequestParam(value = "limit", defaultValue = "10") int limit
    ){
        ScheduleAllResponseDto responseDtoList = scheduleService.findAllSchedulePaging(page-1, limit);
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    @Operation(
            summary = "일정 수정",
            description = "일정의 제목과 내용을 수정합니다",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "수정 성공"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "잘못된 요청"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "로그인을 해야합니다"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "일정을 찾을 수 없음"
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "권한이 없습니다"
                    )
            }
    )
    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @Positive @PathVariable Long scheduleId,
            @Valid @RequestBody UpdateRequestDto requestDto,
            HttpServletRequest httpRequest
    ) {
        Long userId = AuthConst.getUserId(httpRequest);
        ScheduleResponseDto responseDto = scheduleService.updateSchedule(scheduleId, requestDto, userId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Operation(
            summary = "일정 삭제",
            description = "일정 식별자로 일정을 삭제합니다",
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
                            responseCode = "401",
                            description = "로그인이 필요합니다"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "일정을 찾을 수 없음"
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "비밀번호가 틀립니다. 권한이 없습니다."
                    )
            }
    )
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
            @Positive @PathVariable Long scheduleId,
            HttpServletRequest httpRequest
    ) {
        Long userId = AuthConst.getUserId(httpRequest);
        scheduleService.deleteSchedule(scheduleId, userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
