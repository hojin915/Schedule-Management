package com.example.schedulemanagement.comments;

import com.example.schedulemanagement.exception.NotFoundException;
import com.example.schedulemanagement.schedules.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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

    // todoId 별로 comment 개수 세는 쿼리
    // @Query Annotation 사용하면 커스텀 쿼리 사용가능
    @Query("SELECT c.todo.id, COUNT(c) FROM Comment c WHERE c.todo.id IN :todoIdList GROUP BY c.todo.id")
    List<Object[]> countCommentGroupByTodoId(List<Long> todoIdList);
}