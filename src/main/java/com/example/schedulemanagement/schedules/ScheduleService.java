package com.example.schedulemanagement.schedules;

import com.example.schedulemanagement.users.User;
import com.example.schedulemanagement.users.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public ScheduleResponseDto save(Long userId, String title, String contents) {
        User user = userRepository.findByIdOrElseThrow(userId);

        Todo todo = new Todo(title, contents);
        todo.setUser(user);

        Todo savedTodo = scheduleRepository.save(todo);

        return new ScheduleResponseDto(savedTodo);
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

        todo.updateTitle(title);
        todo.updateContents(contents);

        return new ScheduleResponseDto(todo);
    }

    @Transactional
    public void deleteSchedule(Long todoId) {
        Todo todo = scheduleRepository.findByIdOrElseThrow(todoId);
        scheduleRepository.delete(todo);
    }
}