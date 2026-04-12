package com.zjut.student.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerStudentNotFound(StudentNotFoundException ex){
        ErrorResponse errorResponse = new ErrorResponse(404,ex.getMessage(),"2026-04-12");
        return ResponseEntity.status(404).body(errorResponse);
    }
}
