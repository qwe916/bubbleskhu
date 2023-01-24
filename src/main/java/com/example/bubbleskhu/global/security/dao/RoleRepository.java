package com.example.bubbleskhu.global.security.dao;

import com.example.bubbleskhu.global.security.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
