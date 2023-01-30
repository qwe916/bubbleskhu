package com.example.bubbleskhu.bubble.dao.dto.response;

import com.example.bubbleskhu.lesson.domain.Lesson;
import com.example.bubbleskhu.post.domain.Post;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BubbleResponseDTO {
    private Long id;
    private String name;
    private Lesson lesson;
    private Integer presentNumberOfUser;
    private Post post;


}
