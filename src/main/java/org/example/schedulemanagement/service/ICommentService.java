package org.example.schedulemanagement.service;

import org.example.schedulemanagement.dto.commentdto.CommentRequestDto;
import org.example.schedulemanagement.dto.commentdto.CommentResponseDto;
import org.springframework.http.ResponseEntity;

public interface ICommentService {
    CommentResponseDto createComment(Long scheduleId, CommentRequestDto requestDto, Long userId);
    CommentResponseDto updateComment(CommentRequestDto requestDto, Long commentId, Long userId);

}
