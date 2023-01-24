package com.example.bubbleskhu.bubble.domain.dto.request;

import com.example.bubbleskhu.bubble.domain.Bubble;
import lombok.Data;

@Data
public class BubbleRequestDTO {
    private String name;
    private int numberOfUser;
    private Long LessonId;

}
