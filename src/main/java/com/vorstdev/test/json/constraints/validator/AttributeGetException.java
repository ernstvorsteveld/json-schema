package com.vorstdev.test.json.constraints.validator;

public class AttributeGetException extends RuntimeException {

    public AttributeGetException(String attribute) {
        super(attribute);
    }
}
