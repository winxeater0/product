package com.example.product.dto;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public final class ApiResponseBuilder {

    private ApiResponseBuilder() {}

    public static <T> ApiResponse<T> success(
            HttpStatus status,
            T data,
            String message,
            HttpServletRequest request) {

        return new ApiResponse<>(
                status.value(),
                data,
                null,
                message,
                request.getRequestURI(),
                LocalDateTime.now()
        );
    }

    public static ApiResponse<Void> error(
            HttpStatus status,
            String error,
            String message,
            HttpServletRequest request) {

        return new ApiResponse<>(
                status.value(),
                null,
                error,
                message,
                request.getRequestURI(),
                LocalDateTime.now()
        );
    }
}
