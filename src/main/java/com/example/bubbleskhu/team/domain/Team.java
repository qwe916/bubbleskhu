package com.example.bubbleskhu.team.domain;

import com.example.bubbleskhu.lesson.domain.Lesson;
import com.example.bubbleskhu.member.domain.JoinTeam;
import com.example.bubbleskhu.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "team")
    private List<JoinTeam> members = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

}
