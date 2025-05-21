package com.example.schedulemanagement.schedules;

import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<ScheduleResponseDto> save(@RequestBody ScheduleRequestDto dto) {

        ScheduleResponseDto scheduleResponseDto = scheduleService.save(
                        dto.getTitle(),
                        dto.getContents()
                );
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long todoId){
        return new ResponseEntity<>(scheduleService.findScheduleById(todoId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedules() {
        List<ScheduleResponseDto> responseDtoList = scheduleService.findAllSchedules();
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long todoId,
            @RequestBody ScheduleRequestDto dto
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