package com.example.bubbleskhu.post.domain;

import com.example.bubbleskhu.bubble.domain.Bubble;
import com.example.bubbleskhu.post.domain.dto.request.PostRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column
    private String title;
    @Column
    private String content;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "bubble_id")
    private Bubble bubble;

    public void updatePost(PostRequestDTO postRequestDTO) {
        this.title = postRequestDTO.getTitle();
        this.content = postRequestDTO.getContent();
    }

}
