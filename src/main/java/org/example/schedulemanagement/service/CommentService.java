package org.example.schedulemanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.schedulemanagement.dto.commentdto.CommentRequestDto;
import org.example.schedulemanagement.dto.commentdto.CommentResponseDto;
import org.example.schedulemanagement.entity.Comment;
import org.example.schedulemanagement.entity.Schedule;
import org.example.schedulemanagement.entity.User;
import org.example.schedulemanagement.global.ErrorCode;
import org.example.schedulemanagement.global.exception.CustomeException;
import org.example.schedulemanagement.repository.CommentRepository;
import org.example.schedulemanagement.repository.ScheduleRepository;
import org.example.schedulemanagement.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
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


    @Transactional
    @Override
    public CommentResponseDto updateComment(CommentRequestDto requestDto,Long scheduleId ,Long commentId, Long userId) {
        Comment findComment = commentRepository.findCommentByIdOrElseThrow(commentId);
        if(!findComment.getSchedule().getId().equals(scheduleId)){
            throw new CustomeException(ErrorCode.RESOURCE_NOT_FOUND); //
        }
        if(!findComment.getUser().getId().equals(userId)){
            throw new CustomeException(ErrorCode.FORBIDDEN);
        }

        findComment.updateContents(requestDto.getContents());
        Comment updatedComment = commentRepository.findCommentByIdOrElseThrow(commentId);
        return new CommentResponseDto(updatedComment);
    }

    @Override
    public void deleteComment(Long commentId, Long userId) {
        Comment findComment = commentRepository.findCommentByIdOrElseThrow(commentId);
        if(!findComment.getUser().getId().equals(userId)){
            throw new CustomeException(ErrorCode.FORBIDDEN);
        }
        commentRepository.delete(findComment);
    }
}
