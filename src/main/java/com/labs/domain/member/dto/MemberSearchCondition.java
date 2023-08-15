package com.labs.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberSearchCondition {

    private final String username;
    private final String teamName;

    public MemberSearchCondition(String username, String teamName) {
        this.username = username;
        this.teamName = teamName;
    }

}
