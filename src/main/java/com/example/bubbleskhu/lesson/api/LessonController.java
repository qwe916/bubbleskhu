package com.example.bubbleskhu.lesson.api;

import com.example.bubbleskhu.lesson.application.LessonService;
import com.example.bubbleskhu.lesson.dto.response.BubblesByLessonResponseDto;
import com.example.bubbleskhu.lesson.dto.response.LessonsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;

    @GetMapping("/lessons/{id}/bubbles")
    public ResponseEntity<BubblesByLessonResponseDto> findAllBubblesByLessonId(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(lessonService.findAllBubblesByLessonId(id));
    }

    @GetMapping("/lessons")
    public ResponseEntity<LessonsResponseDto> findAllLesson() {
        return ResponseEntity.ok(lessonService.findAllLesson());
    }
}
