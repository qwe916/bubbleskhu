package com.example.bubbleskhu.member.domain;

import com.example.bubbleskhu.model.BaseTime;
import com.example.bubbleskhu.team.domain.Bubble;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "join_team")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class JoinTeam extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "bubble_id")
    private Bubble bubble;
}