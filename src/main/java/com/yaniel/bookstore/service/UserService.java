package com.yaniel.bookstore.service;

import com.yaniel.bookstore.models.entities.User;
import com.yaniel.bookstore.models.payload.CreatedUserDto;
import com.yaniel.bookstore.models.payload.UsersDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    CreatedUserDto save(CreatedUserDto userDto);
    Page<UsersDto> findAll(Pageable pageable);
    UsersDto findById(Long id);
    void deleteById(Long id);
}
