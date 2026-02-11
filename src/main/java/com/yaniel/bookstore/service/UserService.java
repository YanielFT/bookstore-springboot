package com.yaniel.bookstore.service;

import com.yaniel.bookstore.models.payload.users.CreatedUserDto;
import com.yaniel.bookstore.models.payload.users.UsersDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    CreatedUserDto save(CreatedUserDto userDto);
    Page<UsersDto> findAll(Pageable pageable);
    UsersDto findById(Long id);
    UsersDto findByNameOrEmail(String nameOrEmail);
    void deleteById(Long id);
}
