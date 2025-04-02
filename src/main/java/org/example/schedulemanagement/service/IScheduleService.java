package org.example.schedulemanagement.service;

import org.example.schedulemanagement.dto.scheduledto.CreateRequestDto;
import org.example.schedulemanagement.dto.scheduledto.ScheduleAllResponseDto;
import org.example.schedulemanagement.dto.scheduledto.ScheduleResponseDto;
import org.example.schedulemanagement.dto.scheduledto.UpdateRequestDto;
import org.example.schedulemanagement.dto.userdto.DeleteRequestDto;

import java.util.List;

public interface IScheduleService {

    ScheduleResponseDto createSchedule(CreateRequestDto requestDto, Long userId);
    ScheduleResponseDto findSchedule(Long scheduleId);
    ScheduleAllResponseDto findAllSchedulePaging(int page, int limit);
    ScheduleResponseDto updateSchedule(Long scheduleId, UpdateRequestDto requestDto, Long userId);
    void deleteSchedule(Long scheduleId, Long userId);

}
