package com.vorstdev.test.json.schema;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vorstdev.test.json.AbstractValidator;
import com.vorstdev.test.json.JsonValidator;
import org.everit.json.schema.ValidationException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class SchemaValidator extends AbstractValidator implements JsonValidator {

    private final SchemaProvider schemaProvider;

    public SchemaValidator(SchemaProvider schemaProvider, ObjectMapper objectMapper) {
        super(objectMapper);
        this.schemaProvider = schemaProvider;
    }

    public boolean validate(String userFile) {
        JSONObject jsonSubject = new JSONObject(
                new JSONTokener(SchemaValidator.class.getResourceAsStream(userFile)));
        boolean isValid = schema(jsonSubject) && constraints(jsonSubject);
        return isValid;
    }

    private boolean schema(JSONObject jsonSubject) {
        try {
            this.schemaProvider.getSchema().validate(jsonSubject);
            return true;
        } catch (ValidationException e) {
            System.out.println("Type of error: " + e.getKeyword());
            System.out.println("Error message: " + e.getErrorMessage());
            System.out.println("Constraint: " + e.getPointerToViolation());
        }
        return false;
    }

    private boolean constraints(JSONObject jsonSubject) {
        return true;
    }

}
