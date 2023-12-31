package com.labs.domain.member.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.labs.domain.member.domain.Member;
import com.labs.domain.member.dto.MemberSearchCondition;
import com.labs.domain.member.dto.MemberTeamDto;
import com.labs.domain.team.domain.Team;
import com.labs.domain.team.repository.TeamRepository;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    private EntityManager entityManager;

//    @Container
////    static MariaDBContainer mariaDBContainer = new MariaDBContainer()
////            .withDatabaseName("testdb");

    @AfterEach
    void tearDown() {
        memberRepository.deleteAllInBatch();
        teamRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("회원 이름 또는 팀 이름과 일치하는 회원-팀 정보를 조회할 수 있다.")
    @Disabled
    void searchByCondition() {
        // given
        Team teamA = Team.from("teamA");
        Team teamB = Team.from("teamB");
        teamRepository.saveAll(List.of(teamA, teamB));

        Member member1 = Member.of("member1", teamA);
        Member member2 = Member.of("member2", teamA);
        Member member3 = Member.of("member3", teamB);
        Member member4 = Member.of("member4", teamB);
        memberRepository.saveAll(List.of(member1, member2, member3, member4));

        MemberSearchCondition condition = new MemberSearchCondition(null, "teamA");

        // when
        List<MemberTeamDto> memberTeamDtos = memberRepository.searchBy(condition);

        // then
        assertThat(memberTeamDtos).hasSize(2);
    }

    @DisplayName("연관 관계의 PK를 조회할 때는 지연로딩이 없다. (추가 쿼리 안 나감 = N + 1 발생 안 함)")
    @Test
    @Rollback(value = false)
    void getTeamId() {
        // given
        Team teamA = Team.from("teamA");
        Team teamB = Team.from("teamB");
        teamRepository.saveAll(List.of(teamA, teamB));

        Member member1 = Member.of("member1", teamA);
        Member member2 = Member.of("member2", teamA);
        Member member3 = Member.of("member3", teamB);
        Member member4 = Member.of("member4", teamB);
        memberRepository.saveAll(List.of(member1, member2, member3, member4));

        entityManager.flush();
        entityManager.clear();

        // when
        List<Member> members = memberRepository.findAll();

        // then
        members.forEach(member -> System.out.println(member.getTeamId())); // 추가 쿼리 X
        members.forEach(member -> System.out.println(member.getTeam().getName())); // N + 1 발생
    }

}