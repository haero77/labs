package com.labs.postingexample.controller;

import com.labs.postingexample.controller.dto.MemberCreateRequest;
import com.labs.postingexample.controller.dto.MemberCreateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class FooController {

    @PostMapping("/api/members")
    public ResponseEntity<MemberCreateResponse> createMember(
            @RequestBody MemberCreateRequest memberCreateRequest
    ) {
        log.info(memberCreateRequest.toString());

        return ResponseEntity.noContent().build();
    }

}
