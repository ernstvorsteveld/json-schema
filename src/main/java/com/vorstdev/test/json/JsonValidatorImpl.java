package com.vorstdev.test.json;

import com.vorstdev.test.json.constraints.ConstraintsValidator;
import com.vorstdev.test.json.constraints.validator.ConstraintValidator.Operation;
import com.vorstdev.test.json.schema.SchemaValidator;

public class JsonValidatorImpl implements JsonValidator {

    private final JsonValidator schemaValidator;
    private final JsonValidator constraintsValidator;

    public JsonValidatorImpl(SchemaValidator schemaValidator, ConstraintsValidator constraintsValidator) {
        this.schemaValidator = schemaValidator;
        this.constraintsValidator = constraintsValidator;
    }

    @Override
    public boolean validate(Operation operation, String userFile) {
        return schemaValidator.validate(operation, userFile) && constraintsValidator.validate(operation, userFile);
    }
}
