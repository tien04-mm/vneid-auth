package com.thanglong.vneid.infrastructure.adapter.controller;

import com.thanglong.vneid.domain.exception.AccountAlreadyActivatedException;
import com.thanglong.vneid.usecase.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Xử lý lỗi validation từ @Valid (ví dụ: thiếu param, sai format)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(errorMessage, "VALIDATION_FAILED"));
    }

    /**
     * Xử lý lỗi khi tài khoản đã được kích hoạt trước đó
     */
    @ExceptionHandler(AccountAlreadyActivatedException.class)
    public ResponseEntity<ApiResponse<Void>> handleAccountAlreadyActivatedException(AccountAlreadyActivatedException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(ex.getMessage(), "ACCOUNT_ALREADY_ACTIVATED"));
    }

    /**
     * Xử lý lỗi logic nghiệp vụ từ UseCase (ném ra RuntimeException)
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Void>> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(ex.getMessage(), "BUSINESS_ERROR"));
    }

    /**
     * Xử lý các lỗi Exception chung chưa được bắt (tránh trả về lỗi 500 mặc định của Spring làm sập Frontend)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Lỗi hệ thống không xác định: " + ex.getMessage(), "INTERNAL_SERVER_ERROR"));
    }
}
