package com.yaniel.bookstore.models.response;

import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagedApiResponse<T> extends ApiResponse<List<T>> {
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;

    public PagedApiResponse(List<T> data, boolean error, String message, HttpStatus status,
                            int page, int size, long totalElements, int totalPages) {
        super(data, error, message, status);
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public static <T> PagedApiResponse<T> success(Page<T> pageResult, String message, HttpStatus status) {
        return new PagedApiResponse<>(
                pageResult.getContent(),
                false,
                message,
                status,
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.getTotalPages()
        );
    }
}