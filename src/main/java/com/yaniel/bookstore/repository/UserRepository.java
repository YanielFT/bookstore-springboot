package com.yaniel.bookstore.repository;

import com.yaniel.bookstore.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {}
