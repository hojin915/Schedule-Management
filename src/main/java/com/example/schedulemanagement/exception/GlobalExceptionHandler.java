package com.example.schedulemanagement.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ValidateFailException.class)
    public ResponseEntity<ErrorResponseDto> handleValidateFailException(ValidateFailException e){
        log.info("ValidateFail: {}", e.getLogMessage());
        ErrorResponseDto errorResponse = new ErrorResponseDto(e.getMessage(), 401);

        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNotFoundException(NotFoundException e){
        log.info("NotFound: {}", e.getLogMessage());
        ErrorResponseDto errorResponse = new ErrorResponseDto(e.getMessage(), 404);

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
