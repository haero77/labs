package com.labs.hello.global.aop.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonPrettyFormatter {

    private JsonPrettyFormatter() {
    }

    public static String format(ObjectMapper objectMapper, Object source) throws JsonProcessingException {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(source);
        } catch (JsonProcessingException e) {
            log.info("Error occurred while converting {}", source.toString());
            throw e;
        }
    }

}
