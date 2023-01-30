package com.example.bubbleskhu.bubble.domain;

import com.example.bubbleskhu.lesson.domain.Lesson;
import com.example.bubbleskhu.joinTeam.domain.JoinTeam;
import com.example.bubbleskhu.model.BaseTime;
import com.example.bubbleskhu.post.domain.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Bubble extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "bubble")
    private List<JoinTeam> users = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @Column
    private Integer limitNumberOfUser;


    @OneToOne(mappedBy = "bubble")
    private Post post;
}
