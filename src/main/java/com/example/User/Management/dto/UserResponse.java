package com.example.User.Management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
public class UserResponse {
    private UUID id;
    private String fio;
    private String phoneNumber;
    private String avatarUrl;
    private String role;


}
