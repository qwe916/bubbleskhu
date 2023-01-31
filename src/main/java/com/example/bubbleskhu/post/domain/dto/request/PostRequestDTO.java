package com.example.bubbleskhu.post.domain.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostRequestDTO {
    private String title;
    private String content;
}
