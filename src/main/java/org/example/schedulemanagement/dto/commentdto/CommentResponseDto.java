package org.example.schedulemanagement.dto.commentdto;
import lombok.Getter;
import org.example.schedulemanagement.entity.Comment;
import java.time.LocalDateTime;


@Getter
public class CommentResponseDto {

    private final Long id;
    private final String contents;
    private final String userName;
    private final Long scheduleId;
    private final LocalDateTime createAt;
    private final LocalDateTime updatedAt;


    public CommentResponseDto(Comment savedComment){

        this.id = savedComment.getId();
        this.contents = savedComment.getContents();
        this.scheduleId = savedComment.getSchedule().getId();
        this.userName = savedComment.getUser().getName();
        this.createAt = savedComment.getCreatedAt();
        this.updatedAt = savedComment.getUpdatedAt();
    }
}
