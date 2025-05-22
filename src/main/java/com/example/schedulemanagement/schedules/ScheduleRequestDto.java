package com.example.schedulemanagement.schedules;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    @NotNull
    @Size(min = 2, max = 30)
    private final String title;

    @Size(max = 200, message = "maximum 200 characters")
    private final String contents;

    public ScheduleRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}