package com.vorstdev.test.json;

import com.vorstdev.test.json.constraints.validator.ConstraintValidator.Operation;

public interface JsonValidator {

    boolean validate(Operation operation, String userFile);
}
