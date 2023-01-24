package com.example.bubbleskhu.lesson.domain;

import com.example.bubbleskhu.bubble.domain.Bubble;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "lesson")
    private List<Bubble> bubbles = new ArrayList<>();
}
