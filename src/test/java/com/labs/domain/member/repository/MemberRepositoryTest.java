package com.labs.domain.member.repository;

import com.labs.domain.member.domain.Member;
import com.labs.domain.member.dto.MemberSearchCondition;
import com.labs.domain.member.dto.MemberTeamDto;
import com.labs.domain.team.domain.Team;
import com.labs.domain.team.repository.TeamRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamRepository teamRepository;

    @Test
    @DisplayName("회원 이름 또는 팀 이름과 일치하는 회원-팀 정보를 조회할 수 있다.")
    void searchByCondition() {
        // given
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        teamRepository.saveAll(List.of(teamA, teamB));

        Member member1 = new Member("member1", teamA);
        Member member2 = new Member("member2", teamA);
        Member member3 = new Member("member3", teamB);
        Member member4 = new Member("member4", teamB);
        memberRepository.saveAll(List.of(member1, member2, member3, member4));

        MemberSearchCondition condition = new MemberSearchCondition(null, "teamA");

        // when
        List<MemberTeamDto> memberTeamDtos = memberRepository.searchBy(condition);

        // then
        assertThat(memberTeamDtos).hasSize(1);
    }

}