package com.example.bubbleskhu.lesson;

import com.example.bubbleskhu.lesson.domain.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson,Long> {
}
