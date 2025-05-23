package com.example.schedulemanagement.schedules;

import com.example.schedulemanagement.Const;
import com.example.schedulemanagement.PageDto;
import com.example.schedulemanagement.users.UserResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(
            @Valid @RequestBody ScheduleRequestDto dto,
            @SessionAttribute(Const.LOGIN_USER) UserResponseDto user
    ) {

        ScheduleResponseDto scheduleResponseDto = scheduleService.save(
                        user.getId(),
                        dto.getTitle(),
                        dto.getContents()
                );
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(
            @PathVariable Long todoId
    ){
        return new ResponseEntity<>(scheduleService.findScheduleById(todoId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PageDto<SchedulePageResponseDto>> findAllSchedules(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<SchedulePageResponseDto> responseDtoPage = scheduleService.findAllSchedules(page, size);
        return new ResponseEntity<>(new PageDto<>(responseDtoPage), HttpStatus.OK);
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long todoId,
            @Valid @RequestBody ScheduleRequestDto dto
    ){
        ScheduleResponseDto scheduleResponseDto = scheduleService.updateSchedule(
                todoId,
                dto.getTitle(),
                dto.getContents()
        );
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long todoId){
        scheduleService.deleteSchedule(todoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}