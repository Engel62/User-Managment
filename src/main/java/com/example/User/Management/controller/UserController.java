package com.example.User.Management.controller;

import com.example.User.Management.dto.UserRequest;
import com.example.User.Management.dto.UserResponse;
import com.example.User.Management.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    @GetMapping("/users")
    public ResponseEntity<UserResponse> getUser(@RequestParam UUID userID) {
        return ResponseEntity.ok(userService.getUser(userID));
    }

    @PutMapping("/userDetailsUpdate")
    public ResponseEntity<UserResponse> updateUser(
            @RequestParam UUID userID,
            @Valid @RequestBody UserRequest request
    ) {
        return ResponseEntity.ok(userService.updateUser(userID, request));
    }

    @DeleteMapping("/users")
    public ResponseEntity<Void> deleteUser(@RequestParam UUID userID) {
        userService.deleteUser(userID);
        return ResponseEntity.noContent().build();
    }
}

