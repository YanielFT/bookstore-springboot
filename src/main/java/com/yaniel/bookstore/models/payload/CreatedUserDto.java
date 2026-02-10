package com.yaniel.bookstore.models.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatedUserDto {
    private String name;
    private String email;
}
