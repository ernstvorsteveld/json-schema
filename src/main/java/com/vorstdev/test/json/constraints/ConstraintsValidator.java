package com.vorstdev.test.json.constraints;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vorstdev.test.json.AbstractValidator;
import com.vorstdev.test.json.JsonValidator;
import com.vorstdev.test.json.constraints.validator.ConstraintValidator.Operation;
import com.vorstdev.test.json.constraints.validator.ValidatorCollector;
import com.vorstdev.test.json.schema.SchemaDefinition;
import com.vorstdev.test.json.schema.SchemaValidator;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.json.JSONObject;
import org.json.JSONTokener;

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
    public boolean validate(Operation operation, String userFile) {
        JSONObject jsonObject = getUserMap(userFile);
        List<Constraint> failed = this.constraints.getConstraints().stream()
                .filter(c -> validate(operation, jsonObject, c))
                .collect(Collectors.toList());
        return failed.isEmpty();
    }

    private boolean validate(Operation operation, JSONObject jsonObject, Constraint constraint) {
        return validatorCollector.getValidator(constraint.getType()).isInvalid(operation, jsonObject, constraint);
    }

    private JSONObject getUserMap(String userFile) {
            return new JSONObject(new JSONTokener(SchemaValidator.class.getResourceAsStream(userFile)));
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
