package com.vorstdev.test.json.constraints.validator;

import com.vorstdev.test.json.constraints.Constraint;
import java.util.Map;

public class MutabilityValidator implements Validator {

    @Override
    public boolean isInvalid(
            Map<String, Object> toValidate, Constraint constraint) {
        return false;
    }
}
