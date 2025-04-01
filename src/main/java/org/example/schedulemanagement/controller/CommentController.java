package org.example.schedulemanagement.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.example.schedulemanagement.config.HttpGetRequest;
import org.example.schedulemanagement.dto.commentdto.CommentRequestDto;
import org.example.schedulemanagement.dto.commentdto.CommentResponseDto;
import org.example.schedulemanagement.service.ICommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/schedules/{scheduleId}/comments")
@RestController
@RequiredArgsConstructor
public class CommentController {
    private final ICommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(
            @Positive @PathVariable Long scheduleId,
            @Valid @RequestBody CommentRequestDto requestDto,
            HttpServletRequest httpRequest
    ) {
        Long userId = HttpGetRequest.getUserId(httpRequest);
        CommentResponseDto responseDto = commentService.createComment(scheduleId, requestDto, userId);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
