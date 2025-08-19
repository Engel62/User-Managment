package com.example.User.Management.repository;

import com.example.User.Management.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    @Cacheable(value = "users", key = "#id")
    Optional<User> findById(UUID id);
}
