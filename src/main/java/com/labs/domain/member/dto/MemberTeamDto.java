package com.labs.domain.member.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberTeamDto {

    private final Long memberId;
    private final String username;
    private final Long teamId;
    private final String teamName;

    @QueryProjection
    public MemberTeamDto(Long memberId, String username, Long teamId, String teamName) {
        this.memberId = memberId;
        this.username = username;
        this.teamId = teamId;
        this.teamName = teamName;
    }

}
