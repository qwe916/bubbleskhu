package com.example.bubbleskhu.member.dao;

import com.example.bubbleskhu.member.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<User, Long> {

}
