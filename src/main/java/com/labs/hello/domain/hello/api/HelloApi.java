package com.labs.hello.domain.hello.api;


import com.labs.hello.global.aop.log.LogRequestBody;
import com.labs.hello.global.aop.log.LogResponseBody;
import java.util.Map;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloApi {

    @LogRequestBody
    @LogResponseBody
    @GetMapping("/api/hello/requestBody")
    public ResponseEntity<String> mapBody(@RequestBody HelloRequest request) {
        return ResponseEntity.ok()
                .body("String");
    }

    @LogRequestBody
    @LogResponseBody
    @GetMapping("/api/hello/mapBody")
    public ResponseEntity<HelloResponse> mapBody(@RequestBody Map<String, String> body) {
        return ResponseEntity.ok()
                .body(new HelloResponse(15));
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
