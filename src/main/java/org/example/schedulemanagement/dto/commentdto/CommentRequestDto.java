package org.example.schedulemanagement.dto.commentdto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class CommentRequestDto {

    @Getter
    private final String contents;


    @JsonCreator
    public CommentRequestDto(@JsonProperty("contents") String contents) {
        this.contents = contents;
    }
}
