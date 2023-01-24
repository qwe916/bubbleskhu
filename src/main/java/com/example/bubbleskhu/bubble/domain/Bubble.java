package com.example.bubbleskhu.bubble.domain;

import com.example.bubbleskhu.lesson.domain.Lesson;
import com.example.bubbleskhu.member.domain.JoinTeam;
import com.example.bubbleskhu.model.BaseTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Bubble extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "bubble")
    private List<JoinTeam> users = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @Column
    private int numberOfUser;


}
