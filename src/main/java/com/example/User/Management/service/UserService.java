package com.example.User.Management.service;

import com.example.User.Management.dto.UserRequest;
import com.example.User.Management.dto.UserResponse;
import com.example.User.Management.entity.Role;
import com.example.User.Management.entity.User;
import com.example.User.Management.repository.RoleRepository;
import com.example.User.Management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @CacheEvict(value = "users", allEntries = true)
    public UserResponse createUser(UserRequest request) {
        Role role = roleRepository.findByRoleNAme(request.getRole())
                .orElseThrow(()-> new RuntimeException("Роль не найдена"));
        User user = User.builder()
                .fio(request.getFio())
                .phoneNumber(request.getPhoneNumber())
                .avatarUrl(request.getAvatarUrl())
                .role(role)
                .build();

        userRepository.save(user);
        return mapToResponse(user);
    }
    @Cacheable(value = "users", key = "#id")
    public UserResponse getUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        return mapToResponse(user);
    }

    @CacheEvict(value = "users", key = "#id")
    public UserResponse updateUser(UUID id, UserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        Role role = roleRepository.findByRoleNAme(request.getRole())
                .orElseThrow(() -> new RuntimeException("Роль не найдена"));

        user.setFio(request.getFio());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAvatarUrl(request.getAvatarUrl());
        user.setRole(role);

        userRepository.save(user);
        return mapToResponse(user);
    }

    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    private UserResponse mapToResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getFio(),
                user.getPhoneNumber(),
                user.getAvatarUrl(),
                user.getRole().getRoleName()
        );
    }
}

