package com.example.bubbleskhu.bubble.dao;

import com.example.bubbleskhu.bubble.domain.Bubble;
import com.example.bubbleskhu.lesson.domain.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BubbleRepository extends JpaRepository<Bubble, Long> {
    List<Bubble> findByLesson(Lesson lesson);
}
