package org.example.schedulemanagement.dto.scheduledto;

import lombok.Getter;
import org.example.schedulemanagement.entity.Schedule;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class ScheduleAllResponseDto {
    private final int totalPage;
    private final List<ScheduleResponseDto> scheduleResponseDtoList;

    public ScheduleAllResponseDto(Page<Schedule> pageList) {
        this.totalPage = pageList.getTotalPages();
        this.scheduleResponseDtoList = pageList.stream().map(ScheduleResponseDto::new).toList();
    }
}
