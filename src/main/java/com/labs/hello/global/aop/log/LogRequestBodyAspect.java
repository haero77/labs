package com.labs.hello.global.aop.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class LogRequestBodyAspect {

    private final ObjectMapper objectMapper;

    public LogRequestBodyAspect(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Before("@annotation(com.labs.hello.global.aop.log.LogRequestBody) && args(.., @RequestBody bodyParam)")
    public void doLogRequestBody(Object bodyParam) throws JsonProcessingException {
        log.info("[doLogRequestBody]" + System.lineSeparator() + "{}", toJsonFormatString(bodyParam));
    }

    private String toJsonFormatString(Object bodyParam) throws JsonProcessingException {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(bodyParam);
        } catch (JsonProcessingException e) {
            log.info("Error occurred while convert {}", bodyParam.toString());
            throw e;
        }
    }

}
