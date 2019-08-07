package com.vorstdev.test.json.constraints.validator;

public class ValidatorNotFoundException extends RuntimeException {

    public ValidatorNotFoundException(String validatorName) {
        super(validatorName);
    }
}
