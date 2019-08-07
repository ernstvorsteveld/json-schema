package com.vorstdev.test.json;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractValidator {

    private final ObjectMapper objectMapper;

    protected AbstractValidator(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
