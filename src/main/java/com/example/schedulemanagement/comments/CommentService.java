package com.example.schedulemanagement.comments;

import com.example.schedulemanagement.exception.NotFoundException;
import com.example.schedulemanagement.exception.ValidateFailException;
import com.example.schedulemanagement.schedules.ScheduleRepository;
import com.example.schedulemanagement.schedules.Todo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NotFound;
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

    public CommentResponseDto save(Long todoId, Long userId, String contents) {
        Todo todo = scheduleRepository.findByIdOrElseThrow(todoId);

        Comment comment = new Comment(contents);
        comment.setTodo(todo);
        comment.setUserId(userId);

        Comment savedComment = commentRepository.save(comment);

        return new CommentResponseDto(savedComment);
    }

    public CommentResponseDto findCommentById(Long todoId, Long commentId) {
        Comment findComment = commentRepository.findByIdOrElseThrow(commentId);

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
    // 업데이트, 삭제 할 때 댓글 작성자가 로그인한 유저와 동일한지 체크
    public CommentResponseDto updateComment(Long todoId, Long commentId, String contents, Long userId) {
        Comment comment = commentRepository.findByIdOrElseThrow(commentId);
        checkCommentUserId(comment, userId);

        comment.updateContents(contents);

        return new CommentResponseDto(comment);
    }

    @Transactional
    public void deleteComment(Long todoId, Long commentId, Long userId) {
        Comment comment = commentRepository.findByIdOrElseThrow(commentId);
        checkCommentUserId(comment, userId);

        commentRepository.delete(comment);
    }

    // 삭제하려는 댓글의 userId와 요청한 userId를 비교
    private void checkCommentUserId(Comment comment, Long userId){
        if(!comment.getUserId().equals(userId)){
            throw new ValidateFailException(
                    "checkCommentUserId, Requested userId does not match with comment userId" +
                            " requested = " + userId +
                            " comment todoId = " +comment.getUserId(),
                    "Not authorized"
            );
        }
    }
}
