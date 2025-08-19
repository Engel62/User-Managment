package com.example.User.Management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String fio;
    @Column(nullable = false, unique = true)
    private String phone;
    private String avatarUrl;
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}