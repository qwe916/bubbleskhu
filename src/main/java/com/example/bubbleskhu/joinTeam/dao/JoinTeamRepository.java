package com.example.bubbleskhu.joinTeam.dao;

import com.example.bubbleskhu.bubble.domain.Bubble;
import com.example.bubbleskhu.joinTeam.domain.JoinTeam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JoinTeamRepository extends JpaRepository<JoinTeam, Long> {
    List<JoinTeam> findByBubble(Bubble bubble);
}
