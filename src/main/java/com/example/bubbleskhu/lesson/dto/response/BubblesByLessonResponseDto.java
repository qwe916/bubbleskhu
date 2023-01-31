package com.example.bubbleskhu.lesson.dto.response;

import com.example.bubbleskhu.bubble.domain.Bubble;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BubblesByLessonResponseDto {
    List<Bubble> bubbles;
}
