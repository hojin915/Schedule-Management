package com.example.schedulemanagement.comments;

import com.example.schedulemanagement.schedules.ScheduleRepository;
import com.example.schedulemanagement.schedules.ScheduleResponseDto;
import com.example.schedulemanagement.schedules.Todo;
import com.example.schedulemanagement.users.User;
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

        if(!findComment.getTodo().getId().equals(todoId)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Comment not found" + commentId
            );
        }
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

    public CommentResponseDto updateComment(Long todoId, Long commentId, String contents) {
        Comment comment = commentRepository.findByIdOrElseThrow(commentId);
        if(!comment.getTodo().getId().equals(todoId)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Comment not found" + commentId
            );
        }

        comment.updateContents(contents);

        return new CommentResponseDto(comment);
    }
}
