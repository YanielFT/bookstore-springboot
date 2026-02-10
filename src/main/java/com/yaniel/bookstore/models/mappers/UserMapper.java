package com.yaniel.bookstore.models.mappers;

import com.yaniel.bookstore.models.entities.User;
import com.yaniel.bookstore.models.payload.CreatedUserDto;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    CreatedUserDto toCreatedUserDto(User user);
    User toEntity(CreatedUserDto dto);
}
