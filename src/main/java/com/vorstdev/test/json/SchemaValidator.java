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

    private final JsonSchemaSettings jsonSchemaSettings;
    private final JSONObject jsonSchema;
    private final ObjectMapper objectMapper;
    private final Schema schema;

    public SchemaValidator(String schemaFile, ObjectMapper objectMapper) {
        this.jsonSchemaSettings = new JsonSchemaSettings(schemaFile);
        this.jsonSchema = new JSONObject(new JSONTokener(jsonSchemaSettings.getSchema()));
        this.objectMapper = objectMapper;
        this.schema = SchemaLoader.load(jsonSchema);
    }

    public boolean validate(String userFile) {
        JSONObject jsonSubject = new JSONObject(
                new JSONTokener(SchemaValidator.class.getResourceAsStream(userFile)));

        try {
            this.schema.validate(jsonSubject);
            System.out.println(objectMapper.readValue(jsonSubject.toString(), HashMap.class));
            return true;
        } catch (ValidationException e) {
            System.out.println("Type of error: " + e.getKeyword());
            System.out.println("Error message: " + e.getErrorMessage());
            System.out.println("Attribute: " + e.getPointerToViolation());
        } catch (JsonProcessingException e) {
            System.out.println("Not a valid object mapper call, error: " + e.getMessage());
        }
        return false;
    }


}
