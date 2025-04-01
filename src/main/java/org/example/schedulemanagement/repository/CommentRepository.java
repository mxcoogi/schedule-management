package org.example.schedulemanagement.repository;

import org.example.schedulemanagement.entity.Comment;
import org.example.schedulemanagement.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {


    List<Comment> findAllBySchedule(Schedule schedule);
}
