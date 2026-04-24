package com.zjut.campusai.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
@RestControllerAdvice

public class GlobalExceptionHandler {

    @ExceptionHandler({
            StudentNotFoundException.class,
            CourseNotFoundException.class
    })
    public ResponseEntity<ErrorResponse> handleNotFound(RuntimeException ex) {
        return buildResponse(404, ex.getMessage());
    }

    @ExceptionHandler(InvalidScoreException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(RuntimeException ex) {
        return buildResponse(400, ex.getMessage());
    }

    private ResponseEntity<ErrorResponse> buildResponse(int status, String message) {
        ErrorResponse error = new ErrorResponse(status, message, LocalDateTime.now());
        return ResponseEntity.status(status).body(error);
    }
}