package com.labs.hello.domain.hello.api;


import com.labs.hello.global.aop.log.LogResponseBody;
import java.util.Map;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloApi {

    @GetMapping("/api/hello/requestDto")
    public ResponseEntity<String> requestDto(@RequestBody HelloRequest request) {
        return ResponseEntity.ok()
                .body("String");
    }

    @GetMapping("/api/hello/map")
    @LogResponseBody
    public ResponseEntity<Map<String, Integer>> map(@RequestBody Map<String, String> body) {
        return ResponseEntity.ok()
                .body(Map.of("Response", 123));
    }

    @Getter
    public static class HelloRequest {

        private int id;

    }

    @Getter
    public static class HelloResponse {

        private final int id;

        private HelloResponse(int id) {
            this.id = id;
        }

    }

}
