package com.example.schedulemanagement.comments;

import com.example.schedulemanagement.schedules.ScheduleRepository;
import com.example.schedulemanagement.schedules.Todo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    public CommentResponseDto save(Long todoId, String contents) {
        Todo todo = scheduleRepository.findByIdOrElseThrow(todoId);

        Comment comment = new Comment(contents);
        comment.setTodo(todo);

        return new CommentResponseDto(comment);
    }

    public CommentResponseDto findCommentById(Long todoId, Long commentId) {
        Comment findComment = commentRepository.findByIdOrElseThrow(commentId);

        checkCommentTodoId(findComment, todoId);
        return new CommentResponseDto(findComment);
    }

    public List<CommentResponseDto> findAllComments(Long todoId) {
        List<Comment> commentList = commentRepository.findAllByTodoId(todoId);
        List<CommentResponseDto> responseDtoList = new ArrayList<>();
        for(Comment comment : commentList){
            responseDtoList.add(new CommentResponseDto(comment));
        }
        return responseDtoList;
    }

    @Transactional
    public CommentResponseDto updateComment(Long todoId, Long commentId, String contents) {
        Comment comment = commentRepository.findByIdOrElseThrow(commentId);
        checkCommentTodoId(comment, todoId);

        comment.updateContents(contents);

        return new CommentResponseDto(comment);
    }

    @Transactional
    public void deleteComment(Long todoId, Long commentId) {
        Comment comment = commentRepository.findByIdOrElseThrow(commentId);
        checkCommentTodoId(comment, todoId);

        commentRepository.delete(comment);
    }

    private void checkCommentTodoId(Comment comment, Long todoId){
        if(!comment.getTodo().getId().equals(todoId)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Comment not found" + comment.getId()
            );
        }
    }
}
