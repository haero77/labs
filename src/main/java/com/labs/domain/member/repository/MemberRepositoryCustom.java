package com.labs.domain.member.repository;

import com.labs.domain.member.dto.MemberSearchCondition;
import com.labs.domain.member.dto.MemberTeamDto;

import java.util.List;

public interface MemberRepositoryCustom {

    List<MemberTeamDto> searchBy(MemberSearchCondition condition);

}
