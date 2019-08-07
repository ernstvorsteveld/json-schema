package com.vorstdev.test.json.constraints.validator;

import com.vorstdev.test.json.constraints.AttributeConstraint;
import java.util.Map;

public interface Validator {

    boolean isValid(Map<String,Object> toValidate, AttributeConstraint attributeConstraint);

}
