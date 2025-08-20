package com.example.User.Management.service;

import com.example.User.Management.dto.UserRequest;
import com.example.User.Management.dto.UserResponse;
import com.example.User.Management.entity.User;
import com.example.User.Management.repository.UserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    public UserService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Transactional
    @CacheEvict(value = "users", allEntries = true)
    public UserResponse createUser(UserRequest userRequest) {
        if (userRepository.existsByPhoneNumber(userRequest.getPhoneNumber())) {
            throw new RuntimeException("Пользователь с таким номером телефона уже существует");
        }

        User user = new User();
        user.setFio(userRequest.getFio());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setAvatar(userRequest.getAvatar());
        user.setRole(roleService.findOrCreateRole(userRequest.getRoleName()));

        User savedUser = userRepository.save(user);
        return convertToResponse(savedUser);
    }

    @Cacheable(value = "users", key = "#id")
    public UserResponse getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь с номером: " + id + " не найден"));
        return convertToResponse(user);
    }

    @Transactional
    @CachePut(value = "users", key = "#id")
    public UserResponse updateUser(UUID id, UserRequest userRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь с номером: " + id + " не найден"));

        user.setFio(userRequest.getFio());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setAvatar(userRequest.getAvatar());
        user.setRole(roleService.findOrCreateRole(userRequest.getRoleName()));

        User updatedUser = userRepository.save(user);
        return convertToResponse(updatedUser);
    }

    @Transactional
    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь с номером: " + id + " не найден"));

        UUID roleId = user.getRole().getId();
        userRepository.deleteById(id);
        roleService.deleteRoleIfUnused(roleId);
    }

    private UserResponse convertToResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setFio(user.getFio());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setAvatar(user.getAvatar());
        response.setRoleName(user.getRole().getRoleName());
        return response;
    }
}