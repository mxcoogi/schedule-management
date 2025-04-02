package org.example.schedulemanagement.controller;


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

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(
            @Valid @RequestBody CreateRequestDto requestDto,
            HttpServletRequest httpRequest
    ) {
        Long userId= AuthConst.getUserId(httpRequest);
        ScheduleResponseDto responseDto = scheduleService.createSchedule(requestDto, userId);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

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

    @GetMapping
    public ResponseEntity<ScheduleAllResponseDto> findAllSchedule(
            @Positive @RequestParam(value = "page", defaultValue = "1") int page,
            @Positive @RequestParam(value = "limit", defaultValue = "10") int limit
    ){
        ScheduleAllResponseDto responseDtoList = scheduleService.findAllSchedulePaging(page-1, limit);
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

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
