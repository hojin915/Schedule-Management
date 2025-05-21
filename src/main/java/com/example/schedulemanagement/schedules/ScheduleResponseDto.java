package com.example.schedulemanagement.schedules;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto{
    private final Long id;
    private final String title;
    private final String contents;
    private final Long userId;
    private final String username;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ScheduleResponseDto(Long id, String title, String contents, Long userId, String username, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.userId = userId;
        this.username = username;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ScheduleResponseDto(Todo todo){
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.contents = todo.getContents();
        this.userId = todo.getUser().getId();
        this.username = todo.getUser().getUsername();
        this.createdAt = todo.getCreatedAt();
        this.updatedAt = todo.getUpdatedAt();
    }
}