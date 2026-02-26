package com.example.product.exception;

import java.time.LocalDateTime;
import java.util.Map;

public record ValidationError(
        int status,
        String error,
        String path,
        LocalDateTime timestamp,
        Map<String, String> fieldErrors
) {}

