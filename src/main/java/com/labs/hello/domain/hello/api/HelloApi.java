package com.labs.hello.domain.hello.api;


import com.labs.hello.global.aop.log.LogRequestBody;
import java.util.Map;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloApi {

//	@GetMapping("/api/hello/responseEntity")
//	public ResponseEntity<Map<String, String>> responseEntity() {
//		return ResponseEntity.
//	}

    @LogRequestBody
    @GetMapping("/api/hello/requestParam")
    public ResponseEntity<String> requestParam(@RequestBody HelloRequest request) {
        return ResponseEntity.ok()
                .body("String");
    }

    @LogRequestBody
    @GetMapping("/api/hello/mapBody")
    public ResponseEntity<String> requestParam(@RequestBody Map<String, String> body) {
        return ResponseEntity.ok()
                .body("String");
    }


    @Getter
    private static class HelloRequest {

        private int id;

    }

}
