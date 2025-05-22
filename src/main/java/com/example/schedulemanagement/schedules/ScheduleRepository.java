package com.example.schedulemanagement.schedules;

import com.example.schedulemanagement.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Todo, Long> {
    default Todo findByIdOrElseThrow(Long id){
        return findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                "findByIdOrElseThrow(Schedule), Todo not found id = " + id,
                                "Cannot find Schedule"
                        )
                );
    }
}
