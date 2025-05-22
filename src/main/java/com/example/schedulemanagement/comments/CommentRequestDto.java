package com.example.schedulemanagement.comments;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentRequestDto {
    @Size(min = 1, max = 100)
    private final String contents;

    public CommentRequestDto(String contents) {
        this.contents = contents;
    }
}
