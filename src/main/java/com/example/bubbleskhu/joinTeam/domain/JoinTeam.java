package com.example.bubbleskhu.joinTeam.domain;

import com.example.bubbleskhu.bubble.domain.Bubble;
import com.example.bubbleskhu.model.BaseTime;
import com.example.bubbleskhu.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "join_team")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class JoinTeam extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "bubble_id")
    private Bubble bubble;
}