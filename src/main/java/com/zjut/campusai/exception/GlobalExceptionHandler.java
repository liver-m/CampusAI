package com.zjut.campusai.exception;

import com.zjut.campusai.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.stream.Collectors;

@RestControllerAdvice

public class GlobalExceptionHandler {

    @ExceptionHandler({
            StudentNotFoundException.class,
            CourseNotFoundException.class,
            StudentCourseNotFoundException.class
    })
    public ResponseEntity<ApiResponse<?>> handleNotFound(RuntimeException ex) {
        return buildResponse(404, ex.getMessage());
    }

    @ExceptionHandler(InvalidScoreException.class)
    public ResponseEntity<ApiResponse<?>> handleBadRequest(RuntimeException ex) {
        return buildResponse(400, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidation(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return buildResponse(400, message);
    }

    private ResponseEntity<ApiResponse<?>> buildResponse(int code, String message) {
        ApiResponse<?> error = ApiResponse.error(code,message);
        return ResponseEntity.status(code).body(error);
    }
}