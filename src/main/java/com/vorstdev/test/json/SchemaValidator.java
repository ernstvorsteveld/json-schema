package com.vorstdev.test.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

public class SchemaValidator {

    private final ObjectMapper objectMapper;
    private final SchemaProvider schemaProvider;

    public SchemaValidator(SchemaProvider schemaProvider, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.schemaProvider = schemaProvider;
    }

    public boolean validate(String userFile) {
        JSONObject jsonSubject = new JSONObject(
                new JSONTokener(SchemaValidator.class.getResourceAsStream(userFile)));
        boolean isValid = schema(jsonSubject) && constraints(jsonSubject);
        try {
            System.out.println(objectMapper.readValue(jsonSubject.toString(), HashMap.class));
        } catch (JsonProcessingException e) {
            System.out.println("Not a valid object mapper call, error: " + e.getMessage());
        }
        return isValid;
    }

    private boolean schema(JSONObject jsonSubject) {
        try {
            this.schemaProvider.getSchema().validate(jsonSubject);
            return true;
        } catch (ValidationException e) {
            System.out.println("Type of error: " + e.getKeyword());
            System.out.println("Error message: " + e.getErrorMessage());
            System.out.println("Attribute: " + e.getPointerToViolation());
        }
        return false;
    }

    private boolean constraints(JSONObject jsonSubject) {
        return true;
    }

}
