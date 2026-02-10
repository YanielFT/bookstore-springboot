package com.yaniel.bookstore.controllers;

import com.yaniel.bookstore.models.payload.CreatedUserDto;
import com.yaniel.bookstore.models.payload.UsersDto;
import com.yaniel.bookstore.models.response.ApiResponse;
import com.yaniel.bookstore.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<UsersDto>>> users(){
        List<UsersDto> usersDtos = userService.findAll();
        return ResponseEntity.ok(
                ApiResponse.success(usersDtos, "Lista de usuarios obtenida correctamente", HttpStatus.OK)
        );
    }

    @PostMapping("/user/create")
    public ResponseEntity<ApiResponse<CreatedUserDto>> createUser(@Valid @RequestBody CreatedUserDto userDto){
        CreatedUserDto userCreated =  userService.save(userDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(userCreated, "Usuario creado correctamente", HttpStatus.CREATED));

    }

}
