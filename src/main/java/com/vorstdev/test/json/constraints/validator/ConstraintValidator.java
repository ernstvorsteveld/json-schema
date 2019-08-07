package com.vorstdev.test.json.constraints.validator;

import com.vorstdev.test.json.constraints.Constraint;
import java.util.Map;

public interface ConstraintValidator {

    boolean isInvalid(Map<String,Object> toValidate, Constraint constraint);

}
