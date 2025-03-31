package org.example.schedulemanagement.repository;

import org.example.schedulemanagement.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<Schedule> findScheduleById(Long id);
    default Schedule findScheduleByIdOrElseThrow(Long id){
        return findScheduleById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    List<Schedule> findAllScheduleByUserId(Long userId);
}
