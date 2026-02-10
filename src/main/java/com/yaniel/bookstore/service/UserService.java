package com.yaniel.bookstore.service;

import com.yaniel.bookstore.models.payload.CreatedUserDto;

public interface UserService {
    CreatedUserDto save(CreatedUserDto userDto);
}
