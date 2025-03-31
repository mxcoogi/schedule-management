package org.example.schedulemanagement.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulemanagement.dto.scheduledto.CreateRequestDto;
import org.example.schedulemanagement.dto.scheduledto.ScheduleResponseDto;
import org.example.schedulemanagement.entity.Schedule;
import org.example.schedulemanagement.entity.User;
import org.example.schedulemanagement.repository.ScheduleRepository;
import org.example.schedulemanagement.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ScheduleService implements IScheduleService{

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Override
    public ScheduleResponseDto createSchedule(CreateRequestDto requestDto) {

        User user = userRepository.findUserByEmailOrElseThrow(requestDto.getUserEmail());
        if(!user.getPassword().equals(requestDto.getUserPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다");
        }
        Schedule schedule = new Schedule(requestDto.getScheduleTitle(), requestDto.getScheduleContents());
        schedule.setUser(user);
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule.getId(), savedSchedule.getTitle(), savedSchedule.getContents(), user.getName(), savedSchedule.getCreatedAt(), savedSchedule.getUpdatedAt());
    }

    @Override
    public ScheduleResponseDto findSchedule(Long id) {
        Schedule findSchedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        return new ScheduleResponseDto(findSchedule.getId(), findSchedule.getTitle(), findSchedule.getContents(), findSchedule.getUser().getName(), findSchedule.getCreatedAt(), findSchedule.getUpdatedAt());
    }
}
