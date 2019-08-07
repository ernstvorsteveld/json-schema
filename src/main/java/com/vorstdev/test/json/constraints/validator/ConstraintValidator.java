package com.vorstdev.test.json.constraints.validator;

import com.vorstdev.test.json.constraints.Constraint;
import java.util.Map;

public interface ConstraintValidator {

    boolean isInvalid(Operation operation, Map<String,Object> toValidate, Constraint constraint);

    enum Operation {
        Create, Update, Delete
    }

}
