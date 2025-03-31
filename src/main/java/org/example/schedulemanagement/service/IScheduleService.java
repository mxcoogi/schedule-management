package org.example.schedulemanagement.service;

import org.example.schedulemanagement.dto.scheduledto.CreateRequestDto;
import org.example.schedulemanagement.dto.scheduledto.ScheduleResponseDto;
import org.example.schedulemanagement.dto.scheduledto.UpdateRequestDto;

import java.util.List;

public interface IScheduleService {

    ScheduleResponseDto createSchedule(CreateRequestDto requestDto);
    ScheduleResponseDto findSchedule(Long scheduleId);
    List<ScheduleResponseDto> findAllSchedule(Long userId);
    ScheduleResponseDto updateSchedule(Long scheduleId, UpdateRequestDto requestDto);
}
