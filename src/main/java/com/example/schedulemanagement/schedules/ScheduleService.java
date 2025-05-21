package com.example.schedulemanagement.schedules;

import com.example.schedulemanagement.users.User;
import com.example.schedulemanagement.users.UserRepository;
import com.example.schedulemanagement.users.UserResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public ScheduleResponseDto save(String title, String contents) {
        User user = userRepository.findByIdOrElseThrow(1L);

        Todo todo = new Todo(title, contents);
        todo.setUser(user);

        return new ScheduleResponseDto(todo);
    }

    public ScheduleResponseDto findScheduleById(Long todoId) {
        Todo todo = scheduleRepository.findByIdOrElseThrow(todoId);
        return new ScheduleResponseDto(todo);
    }

    public List<ScheduleResponseDto> findAllSchedules() {
        List<ScheduleResponseDto> responseDtoList = new ArrayList<>();
        List<Todo> todoList = scheduleRepository.findAll();
        for(Todo todo : todoList) {
            responseDtoList.add(new ScheduleResponseDto(todo));
        }
        return responseDtoList;
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long todoId, String title, String contents) {
        Todo todo = scheduleRepository.findByIdOrElseThrow(todoId);

        if(title != null){
            todo.updateTitle(title);
        }
        if(contents != null){
            todo.updateContents(contents);
        }

        return new ScheduleResponseDto(todo);
    }

    public void deleteSchedule(Long todoId) {
        Todo todo = scheduleRepository.findByIdOrElseThrow(todoId);
        scheduleRepository.delete(todo);
    }
}