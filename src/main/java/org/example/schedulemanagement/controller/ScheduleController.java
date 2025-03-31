package org.example.schedulemanagement.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.schedulemanagement.dto.scheduledto.CreateRequestDto;
import org.example.schedulemanagement.dto.scheduledto.ScheduleResponseDto;
import org.example.schedulemanagement.dto.scheduledto.UpdateRequestDto;
import org.example.schedulemanagement.dto.userdto.DeleteRequestDto;
import org.example.schedulemanagement.filter.AuthFilter;
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
            @RequestBody CreateRequestDto requestDto,
            HttpServletRequest httpRequest
    ) {
        Long userId= AuthFilter.getUserId(httpRequest);
        ScheduleResponseDto responseDto = scheduleService.createSchedule(requestDto, userId);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> findSchedule(
            @PathVariable Long scheduleId
    ) {
        ScheduleResponseDto schedule = scheduleService.findSchedule(scheduleId);
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedule(
            HttpServletRequest httpRequest
    ) {
        Long userId = AuthFilter.getUserId(httpRequest);
        List<ScheduleResponseDto> responseDtoList = scheduleService.findAllSchedule(userId);
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody UpdateRequestDto requestDto,
            HttpServletRequest httpRequest
    ) {
        Long userId = AuthFilter.getUserId(httpRequest);
        ScheduleResponseDto responseDto = scheduleService.updateSchedule(scheduleId, requestDto, userId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long scheduleId,
            HttpServletRequest httpRequest
    ) {
        Long userId = AuthFilter.getUserId(httpRequest);
        scheduleService.deleteSchedule(scheduleId, userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
