package com.labs.domain.team.domain;

import com.labs.domain.member.domain.Member;
import com.labs.domain.member.repository.MemberRepository;
import com.labs.domain.team.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
@Transactional
class TeamTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Rollback(value = false)
    void teamTest() {
        // given
        Team teamA = Team.from("teamA");
        Team teamB = Team.from("teamB");
        teamRepository.save(teamA);
        teamRepository.save(teamB);

        Member member1 = Member.from("member1");
        member1.changeTeam(teamA);

        Member member2 = Member.from("member2");
        member2.changeTeam(teamB);

        memberRepository.saveAll(List.of(member1, member2));

        em.flush();
        em.clear();

        // when
        List<Team> teams = teamRepository.findAll();

        teams.forEach(team -> team.findMembersBy("teamA"));
    }

}