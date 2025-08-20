package com.example.User.Management.service;

import com.example.User.Management.entity.Role;
import com.example.User.Management.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findOrCreateRole(String roleName) {
        Optional<Role> existingRole = roleRepository.findByRoleName(roleName);
        if (existingRole.isPresent()) {
            return existingRole.get();
        }

        Role newRole = new Role(roleName);
        return roleRepository.save(newRole);
    }

    public void deleteRoleIfUnused(UUID roleId) {
            roleRepository.deleteById(roleId);
    }
}