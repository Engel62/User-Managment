package com.example.User.Management.controller;


import com.example.User.Management.dto.UserRequest;
import com.example.User.Management.dto.UserResponse;
import com.example.User.Management.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createNewUser")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        UserResponse response = userService.createUser(userRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/users")
    public ResponseEntity<UserResponse> getUser(@RequestParam UUID userID) {
        UserResponse response = userService.getUserById(userID);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/userDetailsUpdate")
    public ResponseEntity<UserResponse> updateUser(
            @RequestParam UUID userID,
            @Valid @RequestBody UserRequest userRequest) {
        UserResponse response = userService.updateUser(userID, userRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/users")
    public ResponseEntity<Void> deleteUser(@RequestParam UUID userID) {
        userService.deleteUser(userID);
        return ResponseEntity.noContent().build();
    }
}