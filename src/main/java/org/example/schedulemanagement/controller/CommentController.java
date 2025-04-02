package org.example.schedulemanagement.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.example.schedulemanagement.global.AuthConst;
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
        Long userId = AuthConst.getUserId(httpRequest);
        CommentResponseDto responseDto = commentService.createComment(scheduleId, requestDto, userId);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(
            @Positive @PathVariable Long scheduleId,
            @Positive @PathVariable Long commentId,
            @Valid @RequestBody CommentRequestDto requestDto,
            HttpServletRequest httpRequest
    ){
        Long userId = AuthConst.getUserId(httpRequest);
        CommentResponseDto responseDto = commentService.updateComment(requestDto,scheduleId, commentId, userId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @Positive @PathVariable Long commentId,
            HttpServletRequest httpRequest
    ){
        Long userId = AuthConst.getUserId(httpRequest);
        commentService.deleteComment(commentId, userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
