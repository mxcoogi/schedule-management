package org.example.schedulemanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(
            summary = "댓글 생성",
            description = "특정 일정에 댓글을 작성합니다",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "댓글 작성 성공"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "잘못된 요청"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "로그인 필요합니다"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "일정을 찾을 수 없습니다"
                    )
            }
    )
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

    @Operation(
            summary = "댓글 수정",
            description = "특정 스케줄의 특정 댓글을 수정합니다",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "수정 성공"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "잘못된 요청"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "로그인이 필요합니다"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "일정을 찾을 수 없음"
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "권한이 없습니다. 비밀"
                    )
            }
    )
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
    @Operation(
            summary = "댓글 삭제",
            description = "댓글을 삭제 합니다",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "삭제 성공"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "잘못된 요청"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "로그인이 필요합니다"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "일정을 찾을 수 없음"
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "권한이 없습니다. 비밀번호가 틀립니다."
                    )
            }
    )
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
