package com.vorstdev.test.json.constraints.validator;

import java.util.Map;

public class ValidatorCollector {

    private final Map<String, ConstraintValidator> validatorMap;

    public ValidatorCollector(Map<String, ConstraintValidator> validatorMap) {
        this.validatorMap = validatorMap;
    }

    public ConstraintValidator getValidator(String validatorName) {
        if(validatorMap.containsKey(validatorName)) {
            return validatorMap.get(validatorName);
        }
        throw new ValidatorNotFoundException(validatorName);
    }
}
