package com.example.bubbleskhu.member.dao;

import com.example.bubbleskhu.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
