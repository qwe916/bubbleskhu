package com.example.bubbleskhu.lesson.dto.response;

import com.example.bubbleskhu.lesson.domain.Lesson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LessonsResponseDto {
    private List<Lesson> lessons;
}
