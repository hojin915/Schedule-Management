package com.example.schedulemanagement.comments;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules/{todoId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> save(
            @PathVariable Long todoId,
            @RequestBody CommentRequestDto dto
    ){
        CommentResponseDto commentResponseDto = commentService.save(todoId, dto.getContents());

        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> findCommentById(
            @PathVariable Long todoId,
            @PathVariable Long commentId
    ){
        CommentResponseDto commentResponseDto = commentService.findCommentById(todoId, commentId);
        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> findAllComments(@PathVariable Long todoId){
        List<CommentResponseDto> responseDtoList = commentService.findAllComments(todoId);
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(
            @PathVariable Long todoId,
            @PathVariable Long commentId,
            @RequestBody CommentRequestDto dto
    ){
        CommentResponseDto commentResponseDto = commentService.updateComment(
                todoId,
                commentId,
                dto.getContents()
        );
        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }
}
