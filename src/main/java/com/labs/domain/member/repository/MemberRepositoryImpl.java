package com.labs.domain.member.repository;

import com.labs.domain.member.dto.MemberSearchCondition;
import com.labs.domain.member.dto.MemberTeamDto;
import com.labs.domain.member.dto.QMemberTeamDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.labs.domain.member.domain.QMember.member;
import static com.labs.domain.team.domain.QTeam.team;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<MemberTeamDto> searchBy(MemberSearchCondition condition) {
        return queryFactory
                .select(new QMemberTeamDto(
                        member.id.as("memberId"),
                        member.username,
                        team.id.as("teamId"),
                        team.name.as("teamName")))
                .from(member)
                .leftJoin(member.team, team)
                .where(
                        member.username.eq(condition.getUsername()),
                        team.name.eq(condition.getTeamName())
                )
                .fetch();
    }

}
