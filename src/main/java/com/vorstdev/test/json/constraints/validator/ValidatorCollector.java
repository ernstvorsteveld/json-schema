package com.vorstdev.test.json.constraints.validator;

import java.util.Map;

public class ValidatorCollector {

    private final Map<String, Validator> validatorMap;

    public ValidatorCollector(Map<String, Validator> validatorMap) {
        this.validatorMap = validatorMap;
    }

    public Validator getValidator(String validatorName) {
        if(validatorMap.containsKey(validatorName)) {
            return validatorMap.get(validatorName);
        }
        throw new ValidatorNotFoundException(validatorName);
    }
}
