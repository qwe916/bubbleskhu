package com.example.bubbleskhu.bubble.application;

import com.example.bubbleskhu.bubble.dao.BubbleRepository;
import com.example.bubbleskhu.bubble.domain.Bubble;
import com.example.bubbleskhu.bubble.domain.dto.request.BubbleRequestDTO;
import com.example.bubbleskhu.lesson.LessonRepository;
import com.example.bubbleskhu.lesson.domain.Lesson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BubbleService {
    private final LessonRepository lessonRepository;
    private final BubbleRepository bubbleRepository;
    public void saveBubble(BubbleRequestDTO bubbleRequestDTO) {
        Lesson lesson = lessonRepository.findById(bubbleRequestDTO.getLessonId()).get();
        Bubble bubble = Bubble.builder()
                .name(bubbleRequestDTO.getName())
                .numberOfUser(bubbleRequestDTO.getNumberOfUser())
                .lesson(lesson)
                .build();
        bubbleRepository.save(bubble);
    }
}
