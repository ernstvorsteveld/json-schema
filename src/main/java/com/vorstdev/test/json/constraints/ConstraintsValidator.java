package com.vorstdev.test.json.constraints;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vorstdev.test.json.AbstractValidator;
import com.vorstdev.test.json.JsonValidator;
import com.vorstdev.test.json.schema.SchemaDefinition;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConstraintsValidator extends AbstractValidator implements JsonValidator {

    private final Constraints constraints;

    public ConstraintsValidator(SchemaDefinition schemaDefinition, ObjectMapper objectMapper) {
        super(objectMapper);
        this.constraints = getConstraints(schemaDefinition.getValidators());
    }

    @Override
    public boolean validate(String userFile) {
        Map<String, Object> userMap = getUserMap(userFile);
        return true;
    }

    private Map<String, Object> getUserMap(String userFile) {
        try {
            return getObjectMapper().readValue(ConstraintsValidator.class.getResourceAsStream(userFile), HashMap.class);
        } catch (IOException e) {
            System.out.println("Not a valid object mapper call, error: " + e.getMessage());
            throw new JsonNotValidException(e.getMessage());
        }
    }

    private Constraints getConstraints(String constraintsFile) {
        try {
            return getObjectMapper()
                    .readValue(ConstraintsValidator.class.getResourceAsStream(constraintsFile), Constraints.class);
        } catch (IOException e) {
            throw new JsonNotValidException("Not a valid contraints file, error: " + e.getMessage());
        }
    }
}
