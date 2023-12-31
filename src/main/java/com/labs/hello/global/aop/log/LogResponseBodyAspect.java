package com.labs.hello.global.aop.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.labs.hello.global.aop.util.JsonPrettyFormatter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class LogResponseBodyAspect {

    private final ObjectMapper objectMapper;

    public LogResponseBodyAspect(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Pointcut("@within(com.labs.hello.global.aop.log.LogResponseBody) || "
            + "@annotation(com.labs.hello.global.aop.log.LogResponseBody)")
    public void hasLogResponseBody() {
    }

    @AfterReturning(value = "hasLogResponseBody()", returning = "responseBody")
    public void doLogRequestBody(JoinPoint joinPoint, Object responseBody) throws JsonProcessingException {
        log.info("[doLogResponseBody] {}" + System.lineSeparator() + "{}",
                joinPoint.getSignature(),
                JsonPrettyFormatter.format(this.objectMapper, responseBody));
    }

}
