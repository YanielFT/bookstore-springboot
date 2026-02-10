package com.yaniel.bookstore.models.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {
    private T data;
    private boolean error;
    private String message;
    private HttpStatus status;

    public static <T> ApiResponse<T> success(T data, String message, HttpStatus status) {
        return ApiResponse.<T>builder()
                .data(data)
                .error(false)
                .message(message)
                .status(status)
                .build();
    }

    public static <T> ApiResponse<T> error(String message, HttpStatus status) {
        return ApiResponse.<T>builder()
                .data(null)
                .error(true)
                .message(message)
                .status(status)
                .build();
    }

}

