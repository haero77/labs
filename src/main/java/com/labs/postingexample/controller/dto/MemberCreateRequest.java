package com.labs.postingexample.controller.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MemberCreateRequest {

    private MemberType memberType;
    private String username;

    public enum MemberType {
        ADMIN, NON_ADMIN
    }

}
