package com.example.bubbleskhu.bubble.application;

import com.example.bubbleskhu.bubble.dao.BubbleRepository;
import com.example.bubbleskhu.bubble.domain.Bubble;
import com.example.bubbleskhu.bubble.domain.dto.request.BubbleRequestDTO;
import com.example.bubbleskhu.bubble.domain.dto.response.BubbleResponseDTO;
import com.example.bubbleskhu.global.error.CustomException;
import com.example.bubbleskhu.lesson.LessonRepository;
import com.example.bubbleskhu.lesson.domain.Lesson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BubbleService {
    private final LessonRepository lessonRepository;
    private final BubbleRepository bubbleRepository;
    public void saveBubble(BubbleRequestDTO bubbleRequestDTO) {
        Lesson lesson = lessonRepository.findById(bubbleRequestDTO.getLessonId()).get();
        Bubble bubble = Bubble.builder()
                .name(bubbleRequestDTO.getName())
                .limitNumberOfUser(bubbleRequestDTO.getNumberOfUser())
                .lesson(lesson)
                .build();
        bubbleRepository.save(bubble);
    }

    public BubbleResponseDTO findBubbleById(Long id) {
        Bubble bubble = bubbleRepository.findById(id)
                .orElseThrow(() -> new CustomException("Bubble 존재하지 않습니다.", HttpStatus.NOT_FOUND));
        return BubbleResponseDTO.builder()
                .id(bubble.getId())
                .name(bubble.getName())
                .lesson(bubble.getLesson())
                .presentNumberOfUser(bubble.getUsers().size())
                .build();
    }
}
