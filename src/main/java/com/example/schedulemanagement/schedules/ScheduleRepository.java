package com.example.schedulemanagement.schedules;

import com.example.schedulemanagement.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Todo, Long> {
    Optional<Todo> findTodoByTitle(String title);

    default Todo findByIdOrElseThrow(Long id){
        return findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Schedule not found" + id
                        )
                );
    }
}
