package com.yaniel.bookstore.models.payload.users;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatedUserDto {
    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;
    @Email(message = "El email debe tener un formato válido")
    @NotBlank(message = "El email no puede estar vacío")
    private String email;

    @Size(min = 4, max = 8, message = "El password debe estar entre 4 y 8")
    @Column(length = 8,unique = true)
    private String password;
}
