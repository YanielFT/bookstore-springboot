package com.yaniel.bookstore.controllers;

import com.yaniel.bookstore.models.entities.User;
import com.yaniel.bookstore.models.payload.CreatedUserDto;
import com.yaniel.bookstore.models.payload.UsersDto;
import com.yaniel.bookstore.models.response.ApiResponse;
import com.yaniel.bookstore.models.response.PagedApiResponse;
import com.yaniel.bookstore.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<PagedApiResponse<UsersDto>> users(Pageable pageable){
        Page<UsersDto> usersDtos = userService.findAll(pageable);
        log.debug(usersDtos.getContent().get(0).getName());
        return ResponseEntity.ok(
                PagedApiResponse.success(usersDtos, "Lista de usuarios obtenida correctamente", HttpStatus.OK)
        );
    }

    @PostMapping("/user/create")
    public ResponseEntity<ApiResponse<CreatedUserDto>> createUser(@Valid @RequestBody CreatedUserDto userDto){
        CreatedUserDto userCreated =  userService.save(userDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(userCreated, "Usuario creado correctamente", HttpStatus.CREATED));

    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ApiResponse<UsersDto>> findUser(@PathVariable Long id){
       UsersDto dto = userService.findById(id);

       return ResponseEntity.status(HttpStatus.OK)
               .body(ApiResponse.success(dto,"Usuario",HttpStatus.OK));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Long id){
        UsersDto dto = userService.findById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(null,"Usuario eliminado",HttpStatus.OK));
    }
}
