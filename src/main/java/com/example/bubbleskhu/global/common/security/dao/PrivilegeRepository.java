package com.example.bubbleskhu.global.common.security.dao;

import com.example.bubbleskhu.global.common.security.role.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege,Long> {
}
