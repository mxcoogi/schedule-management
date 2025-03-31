package org.example.schedulemanagement.service;

import org.example.schedulemanagement.dto.scheduledto.CreateRequestDto;
import org.example.schedulemanagement.dto.scheduledto.CreateResponseDto;

public interface IScheduleService {

    CreateResponseDto createSchedule(CreateRequestDto requestDto);
}
