package com.vorstdev.test.json.constraints;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vorstdev.test.json.AbstractValidator;
import com.vorstdev.test.json.JsonValidator;
import com.vorstdev.test.json.constraints.validator.ValidatorCollector;
import com.vorstdev.test.json.schema.SchemaDefinition;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConstraintsValidator extends AbstractValidator implements JsonValidator {

    private final Constraints constraints;
    private final ValidatorCollector validatorCollector;

    public ConstraintsValidator(
            SchemaDefinition schemaDefinition,
            ObjectMapper objectMapper,
            ValidatorCollector validatorCollector) {
        super(objectMapper);
        this.constraints = getConstraints(schemaDefinition.getValidators());
        this.validatorCollector = validatorCollector;
    }

    @Override
    public boolean validate(String userFile) {
        Map<String, Object> userMap = getUserMap(userFile);
        List<Constraint> failed = this.constraints.getConstraints().stream().filter(c -> validate(userMap, c))
                .collect(Collectors.toList());
        return failed.isEmpty();
    }

    private boolean validate(Map<String, Object> userMap, Constraint constraint) {
        return validatorCollector.getValidator(constraint.getType()).isInvalid(userMap, constraint);
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
