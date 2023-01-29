package com.example.bubbleskhu.bubble.dao.dto.request;

import com.example.bubbleskhu.bubble.domain.Bubble;
import com.example.bubbleskhu.lesson.domain.Lesson;
import lombok.Data;

@Data
public class BubbleRequestDTO {
    private String name;
    private int numberOfUser;
    private Lesson lesson;

}
