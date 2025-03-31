package org.example.schedulemanagement.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulemanagement.dto.scheduledto.CreateRequestDto;
import org.example.schedulemanagement.dto.scheduledto.ScheduleResponseDto;
import org.example.schedulemanagement.dto.scheduledto.UpdateRequestDto;
import org.example.schedulemanagement.dto.userdto.DeleteRequestDto;
import org.example.schedulemanagement.entity.Schedule;
import org.example.schedulemanagement.entity.User;
import org.example.schedulemanagement.repository.ScheduleRepository;
import org.example.schedulemanagement.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
    public ScheduleResponseDto findSchedule(Long scheduleId) {
        Schedule findSchedule = scheduleRepository.findScheduleByIdOrElseThrow(scheduleId);

        return new ScheduleResponseDto(findSchedule.getId(), findSchedule.getTitle(), findSchedule.getContents(), findSchedule.getUser().getName(), findSchedule.getCreatedAt(), findSchedule.getUpdatedAt());
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedule(Long userId) {
        User user = userRepository.findUserByIdOrElseThrow(userId);
        List<Schedule>  scheduleList = scheduleRepository.findAllScheduleByUserId(user.getId());
        return scheduleList.stream().map(this::toScheduleResponseDto).toList();
    }

    @Transactional
    @Override
    public ScheduleResponseDto updateSchedule(Long scheduleId, UpdateRequestDto requestDto) {
        Schedule findSchedule = scheduleRepository.findScheduleByIdOrElseThrow(scheduleId);
        User findUser = findSchedule.getUser();
        if (!findUser.getEmail().equals(requestDto.getUserEmail()) || !findUser.getPassword().equals(requestDto.getUserPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호가 일치하지 않습니다");
        }
        findSchedule.updateSchedule(requestDto.getUpdateScheduleTitle(), requestDto.getUpdateScheduleContents());
        Schedule updatedSchedule = scheduleRepository.findScheduleByIdOrElseThrow(scheduleId);

        return toScheduleResponseDto(updatedSchedule);
    }

    @Override
    @Transactional
    public void deleteSchedule(Long scheduleId, DeleteRequestDto requestDto) {
        Schedule findSchedule = scheduleRepository.findScheduleByIdOrElseThrow(scheduleId);
        User findUser = findSchedule.getUser();
        if (!findUser.getPassword().equals(requestDto.getUserPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호가 일치하지 않습니다");
        }
        scheduleRepository.delete(findSchedule);
    } //여기 바꿔야뎀

    private ScheduleResponseDto toScheduleResponseDto(Schedule schedule){
        return new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getContents(), schedule.getUser().getName(), schedule.getCreatedAt(), schedule.getUpdatedAt());
    }


}
