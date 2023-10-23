package com.labs.domain.member.domain;

import com.labs.domain.team.domain.Team;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @Builder
    protected Member(String username, Team team) {
        this.username = username;
        this.team = team;
    }

    public static Member of(String username, Team team) {
        return Member.builder()
                .username(username)
                .team(team)
                .build();
    }

    public void changeTeam(Team team) {
        if (this.team != null) { // 기존 팀과 연관 관계 제거
            this.team.removeMember(this);
        }

        this.team = team;
        team.addMember(this);
    }

}
