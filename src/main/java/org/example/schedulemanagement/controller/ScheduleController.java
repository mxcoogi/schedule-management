package org.example.schedulemanagement.controller;


import lombok.RequiredArgsConstructor;
import org.example.schedulemanagement.dto.scheduledto.CreateRequestDto;
import org.example.schedulemanagement.dto.scheduledto.ScheduleResponseDto;
import org.example.schedulemanagement.service.IScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final IScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(
            @RequestBody CreateRequestDto requestDto
            ){

        ScheduleResponseDto responseDto = scheduleService.createSchedule(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> findSchedule(
            @PathVariable Long scheduleId
    ){
        ScheduleResponseDto schedule = scheduleService.findSchedule(scheduleId);
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedule(
            @PathVariable Long userId
    ){
        List<ScheduleResponseDto> responseDtoList = scheduleService.findAllSchedule(userId);
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }


}
