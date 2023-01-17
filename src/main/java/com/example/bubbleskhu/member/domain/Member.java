package com.example.bubbleskhu.member.domain;

import com.example.bubbleskhu.major.domain.Major;
import com.example.bubbleskhu.model.Grade;
import com.example.bubbleskhu.model.Role;
import com.example.bubbleskhu.team.domain.Team;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
    private String name;
    @OneToOne
    private Major major;
    @Enumerated(EnumType.STRING)
    @Column
    private Grade grade;
    @Column
    private String userId;
    @Column
    private String password;
    @Enumerated(EnumType.STRING)
    @Column
    private Role role;
    @OneToMany(mappedBy = "member")
    private List<JoinTeam> teams = new ArrayList<>();
}
