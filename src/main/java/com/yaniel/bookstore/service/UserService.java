package com.yaniel.bookstore.service;

import com.yaniel.bookstore.models.payload.CreatedUserDto;
import com.yaniel.bookstore.models.payload.UsersDto;

import java.util.List;

public interface UserService {
    CreatedUserDto save(CreatedUserDto userDto);
    List<UsersDto> findAll();
}
