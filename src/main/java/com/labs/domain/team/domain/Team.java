package com.labs.domain.team.domain;

import com.labs.domain.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team")
    private final List<Member> members = new ArrayList<>();

    @Builder
    protected Team(String name) {
        this.name = name;
    }

    public static Team from(String name) {
        return Team.builder()
                .name(name)
                .build();
    }

    public void addMember(Member member) {
        this.members.add(member);
    }

    public void removeMember(Member member) {
        this.members.remove(member);
    }

}
