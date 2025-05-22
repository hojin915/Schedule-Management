package com.example.schedulemanagement.comments;

import com.example.schedulemanagement.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByTodoId(Long id);

    default Comment findByIdOrElseThrow(Long id){
        return findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                "findByIdOrElseThrow(Comment), Comment not found id = " + id,
                                "Cannot find Comment"
                        )
                );
    }
}
