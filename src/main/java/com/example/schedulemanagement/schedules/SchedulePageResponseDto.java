package com.example.schedulemanagement.schedules;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SchedulePageResponseDto {
    private final Long id;
    private final String title;
    private final String contents;
    private final String username;
    private final Long commentCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public SchedulePageResponseDto (Long todoId, String title, String contents, String username, Long commentCount, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = todoId;
        this.title = title;
        this.contents = contents;
        this.username = username;
        this.commentCount = commentCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public SchedulePageResponseDto(Todo todo, Long commentCount){
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.contents = todo.getContents();
        this.username = todo.getUser().getUsername();
        this.commentCount = commentCount;
        this.createdAt = todo.getCreatedAt();
        this.updatedAt = todo.getUpdatedAt();
    }
}
