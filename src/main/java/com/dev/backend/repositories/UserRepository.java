package com.dev.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import com.dev.backend.models.User;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
}
