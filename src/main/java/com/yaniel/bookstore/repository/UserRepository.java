package com.yaniel.bookstore.repository;

import com.yaniel.bookstore.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByNameOrEmail(String username, String email);
    Optional<User> findByEmail(String email);
    Optional<User> findByName(String username);
    Boolean existsByName(String username);
    Boolean existsByEmail(String email);
}
