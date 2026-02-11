package com.yaniel.bookstore.service;

import com.yaniel.bookstore.models.entities.User;
import com.yaniel.bookstore.models.payload.auth.LoginDto;
import com.yaniel.bookstore.models.payload.users.CreatedUserDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(CreatedUserDto createdUserDto);
}
