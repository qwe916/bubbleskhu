package com.example.bubbleskhu.lesson.application;

import com.example.bubbleskhu.bubble.dao.BubbleRepository;
import com.example.bubbleskhu.lesson.dao.LessonRepository;
import com.example.bubbleskhu.lesson.domain.Lesson;
import com.example.bubbleskhu.lesson.dto.response.BubblesByLessonResponseDto;
import com.example.bubbleskhu.lesson.dto.response.LessonsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;
    private final BubbleRepository bubbleRepository;
    public LessonsResponseDto findAllLesson() {
        return LessonsResponseDto.builder()
                .lessons(lessonRepository.findAll())
                .build();
    }

    public BubblesByLessonResponseDto findAllBubblesByLessonId(Long id) {
        Lesson lesson = lessonRepository.findById(id).get();
        return BubblesByLessonResponseDto.builder()
                .bubbles(bubbleRepository.findByLesson(lesson))
                .build();
    }
}
