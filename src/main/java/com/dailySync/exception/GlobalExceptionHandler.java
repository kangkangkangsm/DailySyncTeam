package com.dailySync.exception;

import com.dailySync.common.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
//    private final ApiResponse apiResponse = new ApiResponse();

    // 예외 처리 메서드
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        return ApiResponse.error("문제 발생: " + e.getMessage());
    }
    // 리소스가 존재하지 않을 때
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFound(EntityNotFoundException ex) {
        return ApiResponse.fail("Resource not found", null);
    }
    // 인증/인가 관련 예외 처리
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException ex) {
        return ApiResponse.fail("Access denied", null);
    }
}
