package org.example.schedulemanagement.repository;

import lombok.NonNull;
import org.example.schedulemanagement.entity.Schedule;
import org.example.schedulemanagement.global.ErrorCode;
import org.example.schedulemanagement.global.exception.CustomeException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<Schedule> findScheduleById(Long id);
    default Schedule findScheduleByIdOrElseThrow(Long id){
        return findScheduleById(id).orElseThrow(()->new CustomeException(ErrorCode.RESOURCE_NOT_FOUND));
    }
    @NonNull
    Page<Schedule> findAll(@NonNull Pageable pageable);
}
