package com.example.User.Management.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String fio;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    private String avatarUrl;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}