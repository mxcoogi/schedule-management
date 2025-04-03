package org.example.schedulemanagement.service;

import org.example.schedulemanagement.dto.scheduledto.CreateRequestDto;
import org.example.schedulemanagement.dto.scheduledto.ScheduleAllResponseDto;
import org.example.schedulemanagement.dto.scheduledto.ScheduleResponseDto;
import org.example.schedulemanagement.dto.scheduledto.ScheduleUpdateRequestDto;

public interface IScheduleService {

    ScheduleResponseDto createSchedule(CreateRequestDto requestDto, Long userId);
    ScheduleResponseDto findSchedule(Long scheduleId);
    ScheduleAllResponseDto findAllSchedulePaging(int page, int limit);
    ScheduleResponseDto updateSchedule(Long scheduleId, ScheduleUpdateRequestDto requestDto, Long userId);
    void deleteSchedule(Long scheduleId, Long userId);

}
