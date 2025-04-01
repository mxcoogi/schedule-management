package org.example.schedulemanagement.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulemanagement.dto.commentdto.CommentRequestDto;
import org.example.schedulemanagement.dto.commentdto.CommentResponseDto;
import org.example.schedulemanagement.entity.Comment;
import org.example.schedulemanagement.entity.Schedule;
import org.example.schedulemanagement.entity.User;
import org.example.schedulemanagement.repository.CommentRepository;
import org.example.schedulemanagement.repository.ScheduleRepository;
import org.example.schedulemanagement.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService{
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;


    @Override
    public CommentResponseDto createComment(Long scheduleId, CommentRequestDto requestDto, Long userId) {
        User findUser = userRepository.findUserByIdOrElseThrow(userId);
        Schedule findSchedule = scheduleRepository.findScheduleByIdOrElseThrow(scheduleId);
        Comment comment = new Comment(requestDto.getContents());
        comment.setSchedule(findSchedule);
        comment.setUser(findUser);
        Comment savedComment = commentRepository.save(comment);
        return new CommentResponseDto(savedComment);
    }
}
