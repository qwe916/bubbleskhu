package com.example.bubbleskhu.bubble.dao;

import com.example.bubbleskhu.bubble.domain.Bubble;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BubbleRepository extends JpaRepository<Bubble, Long> {

}
