package com.example.schedulemanagement.comments;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private final Long id;
    private final Long todoId;
    private final Long userId;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public CommentResponseDto(Long id, Long todoId, Long userId, String contents, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.todoId = todoId;
        this.userId = userId;
        this.contents = contents;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.todoId = comment.getTodo().getId();
        this.userId = comment.getUserId();
        this.contents = comment.getContents();
        this.createdAt = comment.getCreatedAt();
        this.updatedAt = comment.getUpdatedAt();
    }
}
