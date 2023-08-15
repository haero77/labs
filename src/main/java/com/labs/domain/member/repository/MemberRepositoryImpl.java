package com.labs.domain.member.repository;

import com.labs.domain.member.dto.MemberSearchCondition;
import com.labs.domain.member.dto.MemberTeamDto;
import com.labs.domain.member.dto.QMemberTeamDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.function.Supplier;

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
                        searchConditionEquals(condition)
                )
                .fetch();
    }

    public BooleanBuilder searchConditionEquals(MemberSearchCondition condition) {
        return usernameEquals(condition.getUsername())
                .and(teamNameEquals(condition.getTeamName()));
    }

    public BooleanBuilder usernameEquals(String username) {
        return nullSafeBooleanBuilder(() -> member.username.eq(username));
    }

    public BooleanBuilder teamNameEquals(String teamName) {
        return nullSafeBooleanBuilder(() -> team.name.eq(teamName));
    }

    private BooleanBuilder nullSafeBooleanBuilder(Supplier<BooleanExpression> supplier) {
        try {
            return new BooleanBuilder(supplier.get());
        } catch (IllegalArgumentException e) {
            return new BooleanBuilder();
        }
    }

}
