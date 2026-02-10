package com.yaniel.bookstore.service.impl;

import com.yaniel.bookstore.errors.exception.ResourceNotFoundException;
import com.yaniel.bookstore.models.entities.User;
import com.yaniel.bookstore.models.mappers.UserMapper;
import com.yaniel.bookstore.models.payload.CreatedUserDto;
import com.yaniel.bookstore.models.payload.UsersDto;
import com.yaniel.bookstore.repository.UserRepository;
import com.yaniel.bookstore.service.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository){
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public CreatedUserDto save(CreatedUserDto createdUserDto) {
        User user = userMapper.toEntity(createdUserDto);          // DTO → Entity
        User saved = userRepository.save(user);
        return userMapper.toCreatedUserDto(saved);
    }

    @Override
    public Page<UsersDto> findAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::toDto);
    }

    @Override
    public UsersDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User","id",id));
        return userMapper.toDto(user);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        userRepository.deleteById(id);
    }

}
