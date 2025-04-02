package org.example.schedulemanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.schedulemanagement.dto.commentdto.CommentResponseDto;
import org.example.schedulemanagement.dto.scheduledto.CreateRequestDto;
import org.example.schedulemanagement.dto.scheduledto.ScheduleAllResponseDto;
import org.example.schedulemanagement.dto.scheduledto.ScheduleResponseDto;
import org.example.schedulemanagement.dto.scheduledto.UpdateRequestDto;
import org.example.schedulemanagement.entity.Schedule;
import org.example.schedulemanagement.entity.User;
import org.example.schedulemanagement.global.ErrorCode;
import org.example.schedulemanagement.global.exception.CustomeException;
import org.example.schedulemanagement.repository.CommentRepository;
import org.example.schedulemanagement.repository.ScheduleRepository;
import org.example.schedulemanagement.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private final CommentRepository commentRepository;

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
        List<CommentResponseDto> commentList = commentRepository.findAllBySchedule(findSchedule)
                .stream().map(CommentResponseDto::new).toList();
        return new ScheduleResponseDto(findSchedule, commentList);
    }


    @Override
    public ScheduleAllResponseDto findAllSchedulePaging(int page, int limit){
        Pageable pageable = PageRequest.of(page, limit);
        Page<Schedule> pageList = scheduleRepository.findAll(pageable);
        return new ScheduleAllResponseDto(pageList);
    }

    @Transactional
    @Override
    public ScheduleResponseDto updateSchedule(Long scheduleId, UpdateRequestDto requestDto, Long userId) {
        Schedule findSchedule = scheduleRepository.findScheduleByIdOrElseThrow(scheduleId);
        User findUser = findSchedule.getUser();
        if (!findUser.getId().equals(userId)) {
            log.info("{} {}", findUser.getId(), userId);
            throw new CustomeException(ErrorCode.FORBIDDEN);
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
            throw new CustomeException(ErrorCode.FORBIDDEN);
        }
        scheduleRepository.delete(findSchedule);
    }

}
