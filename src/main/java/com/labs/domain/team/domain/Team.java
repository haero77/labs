package com.labs.domain.team.domain;

import com.labs.domain.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    private String name;

    @BatchSize(size = 100)
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    @Builder
    protected Team(String name) {
        this.name = name;
    }

    public static Team from(String name) {
        return Team.builder()
                .name(name)
                .build();
    }

    public List<Member> findMembersBy(String username) {
        return this.members.stream()
                .filter(member -> member.getUsername().equals(username))
                .collect(Collectors.toList());
    }

    public void addMember(Member member) {
        this.members.add(member);
    }

    public void removeMember(Member member) {
        this.members.remove(member);
    }

}
