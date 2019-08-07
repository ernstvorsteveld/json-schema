package com.vorstdev.test.json.constraints.validator;

import com.vorstdev.test.json.constraints.Constraint;
import java.util.Map;

public class MutabilityConstraintValidator implements ConstraintValidator {

    @Override
    public boolean isInvalid(Operation operation, Map<String, Object> toValidate, Constraint constraint) {
        return false;
    }
}
