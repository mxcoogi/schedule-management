package org.example.schedulemanagement.service;

import org.example.schedulemanagement.dto.scheduledto.CreateRequestDto;
import org.example.schedulemanagement.dto.scheduledto.ScheduleResponseDto;

public interface IScheduleService {

    ScheduleResponseDto createSchedule(CreateRequestDto requestDto);
    ScheduleResponseDto findSchedule(Long id);
}
