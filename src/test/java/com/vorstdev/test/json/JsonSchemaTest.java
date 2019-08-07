package com.vorstdev.test.json;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vorstdev.test.json.constraints.ConstraintsValidator;
import com.vorstdev.test.json.constraints.validator.CaseExactValidator;
import com.vorstdev.test.json.constraints.validator.MutabilityValidator;
import com.vorstdev.test.json.constraints.validator.ReturnedValidator;
import com.vorstdev.test.json.constraints.validator.UniquenessValidator;
import com.vorstdev.test.json.constraints.validator.Validator;
import com.vorstdev.test.json.constraints.validator.ValidatorCollector;
import com.vorstdev.test.json.schema.SchemaDefinition;
import com.vorstdev.test.json.schema.SchemaProvider;
import com.vorstdev.test.json.schema.SchemaValidator;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class JsonSchemaTest {

    @Test
    public void should_read_schema() {
        SchemaDefinition schemaDefinition = new SchemaDefinition(
                "/com/vorstdev/test/json/schema.json", "/com/vorstdev/test/json/validators.json");
        JsonSchemaSettings jsonSchemaSettings = new JsonSchemaSettings(schemaDefinition);
        SchemaProvider schemaProvider = new SchemaProvider(jsonSchemaSettings);
        ObjectMapper objectMapper = new ObjectMapper();
        SchemaValidator schemaValidator = new SchemaValidator(schemaProvider, objectMapper);
        ConstraintsValidator constraintsValidator = new ConstraintsValidator(schemaDefinition, objectMapper,
                getValidatorCollector());

        JsonValidator jsonValidator = new JsonValidatorImpl(schemaValidator, constraintsValidator);

        assertThat(jsonValidator.validate("/com/vorstdev/test/json/user.json")).isTrue();
        assertThat(jsonValidator.validate("/com/vorstdev/test/json/user-error0.json")).isFalse();
        assertThat(jsonValidator.validate("/com/vorstdev/test/json/user-error1.json")).isFalse();
        assertThat(jsonValidator.validate("/com/vorstdev/test/json/user-error2.json")).isFalse();
    }

    private ValidatorCollector getValidatorCollector() {
        Map<String, Validator> validatorMap = new HashMap<>();
        validatorMap.put("uniqueness", new UniquenessValidator());
        validatorMap.put("returned", new ReturnedValidator());
        validatorMap.put("caseExact", new CaseExactValidator());
        validatorMap.put("mutability", new MutabilityValidator());
        return  new ValidatorCollector(validatorMap);
    }

}
