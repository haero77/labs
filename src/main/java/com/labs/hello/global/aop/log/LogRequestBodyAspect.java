package com.labs.hello.global.aop.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.labs.hello.global.aop.util.JsonPrettyFormatter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class LogRequestBodyAspect {

    private final ObjectMapper objectMapper;

    public LogRequestBodyAspect(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Pointcut("@within(com.labs.hello.global.aop.log.LogRequestBody) || "
            + "@annotation(com.labs.hello.global.aop.log.LogRequestBody)")
    public void hasLogRequestBody() {
    }

    @Before("hasLogRequestBody() && args(.., @org.springframework.web.bind.annotation.RequestBody bodyParam)")
    public void doLogRequestBody(JoinPoint joinPoint, Object bodyParam) throws JsonProcessingException {
        log.info("[doLogRequestBody] {}" + System.lineSeparator() + "{}",
                joinPoint.getSignature(),
                JsonPrettyFormatter.format(this.objectMapper, bodyParam));
    }

}
