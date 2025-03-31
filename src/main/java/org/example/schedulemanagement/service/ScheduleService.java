package org.example.schedulemanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.schedulemanagement.dto.scheduledto.CreateRequestDto;
import org.example.schedulemanagement.dto.scheduledto.ScheduleResponseDto;
import org.example.schedulemanagement.dto.scheduledto.UpdateRequestDto;
import org.example.schedulemanagement.entity.Schedule;
import org.example.schedulemanagement.entity.User;
import org.example.schedulemanagement.repository.ScheduleRepository;
import org.example.schedulemanagement.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleService implements IScheduleService{

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Override
    public ScheduleResponseDto createSchedule(CreateRequestDto requestDto, Long userId) {

        User user = userRepository.findUserByIdOrElseThrow(userId);
        Schedule schedule = new Schedule(requestDto.getScheduleTitle(), requestDto.getScheduleContents());
        schedule.setUser(user);
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule);
    }

    @Override
    public ScheduleResponseDto findSchedule(Long scheduleId) {
        Schedule findSchedule = scheduleRepository.findScheduleByIdOrElseThrow(scheduleId);

        return new ScheduleResponseDto(findSchedule);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedule(Long userId) {
        User user = userRepository.findUserByIdOrElseThrow(userId);
        List<Schedule> scheduleList = scheduleRepository.findAllScheduleByUserId(user.getId());
        return scheduleList.stream().map(ScheduleResponseDto::new).toList();
    }

    @Transactional
    @Override
    public ScheduleResponseDto updateSchedule(Long scheduleId, UpdateRequestDto requestDto, Long userId) {
        Schedule findSchedule = scheduleRepository.findScheduleByIdOrElseThrow(scheduleId);
        User findUser = findSchedule.getUser();
        if (!findUser.getId().equals(userId)) {
            log.info("{} {}", findUser.getId(), userId);
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "본인 게시물이 아닙니다.");
        }
        findSchedule.updateSchedule(requestDto.getUpdateScheduleTitle(), requestDto.getUpdateScheduleContents());
        Schedule updatedSchedule = scheduleRepository.findScheduleByIdOrElseThrow(scheduleId);

        return new ScheduleResponseDto(updatedSchedule);
    }

    @Override
    @Transactional
    public void deleteSchedule(Long scheduleId, Long userId) {
        Schedule findSchedule = scheduleRepository.findScheduleByIdOrElseThrow(scheduleId);
        User findUser = findSchedule.getUser();
        if (!findUser.getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "본인 게시글이 아닙니다");
        }
        scheduleRepository.delete(findSchedule);
    } //여기 바꿔야뎀




}
