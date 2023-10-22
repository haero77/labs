package com.labs.postingexample.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MemberCreateResponse {

    @JsonProperty("isAdmin")
    private boolean isAdmin;
    private String username;

    public MemberCreateResponse(boolean isAdmin, String username) {
        this.isAdmin = isAdmin;
        this.username = username;
    }

}
