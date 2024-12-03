package com.dailySync.common;

import lombok.*;
import org.springframework.http.ResponseEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private String status;  // success, fail, error
    private String message; // 상태 메시지
    private T data;         // 데이터 또는 에러 정보

    // 성공 응답
    public static <T> ResponseEntity<ApiResponse<T>> success(T data) {
        ApiResponse<T> response = new ApiResponse<>("success", null, data);
        return ResponseEntity.ok(response);
    }

    // 실패 응답
    public static <T> ResponseEntity<ApiResponse<T>> fail(String message, T errors) {
        ApiResponse<T> response = new ApiResponse<>("fail", message, errors);
        return ResponseEntity.badRequest().body(response);
    }

    // 에러 응답
    public static ResponseEntity<ApiResponse<Void>> error(String message) {
        ApiResponse<Void> response = new ApiResponse<>("error", message, null);
        return ResponseEntity.internalServerError().body(response);
    }
}


