package com.yaniel.bookstore.models.mappers;

import com.yaniel.bookstore.models.entities.User;
import com.yaniel.bookstore.models.payload.CreatedUserDto;

import com.yaniel.bookstore.models.payload.UsersDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UsersDto toDto(User user);
    CreatedUserDto toCreatedUserDto(User user);
    User toEntity(CreatedUserDto dto);
}
