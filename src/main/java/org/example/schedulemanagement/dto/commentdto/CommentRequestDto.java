package org.example.schedulemanagement.dto.commentdto;

import lombok.Getter;

public class CommentRequestDto {

    @Getter
    private final String contents;


    public CommentRequestDto(String contents) {
        this.contents = contents;
    }
}
