package com.example.bubbleskhu.bubble.domain.dto.response;

import com.example.bubbleskhu.lesson.domain.Lesson;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BubbleResponseDTO {
    private Long id;
    private String name;
    private Lesson lesson;
    private Integer presentNumberOfUser;



}
