package com.yaniel.bookstore.controllers;

import com.yaniel.bookstore.models.payload.CreatedUserDto;
import com.yaniel.bookstore.models.response.ApiResponse;
import com.yaniel.bookstore.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<String> users(){
        return ResponseEntity.ok("some users");
    }

    @PostMapping("/user/create")
    public ResponseEntity<ApiResponse<CreatedUserDto>> createUser(@RequestBody CreatedUserDto userDto){
        CreatedUserDto userCreated =  userService.save(userDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(userCreated, "Usuario creado correctamente", HttpStatus.CREATED));

    }

}
