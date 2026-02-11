package com.yaniel.bookstore.models.payload.users;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UsersDto {
    private Long id;
    private String name;
    private String email;
    private String username;
    private Boolean enabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
