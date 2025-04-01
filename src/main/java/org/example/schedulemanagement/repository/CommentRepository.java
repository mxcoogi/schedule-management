package org.example.schedulemanagement.repository;

import org.example.schedulemanagement.dto.commentdto.CommentRequestDto;
import org.example.schedulemanagement.entity.Comment;
import org.example.schedulemanagement.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllBySchedule(Schedule schedule);

    default Comment findCommentByIdOrElseThrow(Long commentId){
        return findById(commentId).orElseThrow(()->(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }
}
