package com.vorstdev.test.json;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vorstdev.test.json.constraints.ConstraintsValidator;
import com.vorstdev.test.json.schema.SchemaDefinition;
import com.vorstdev.test.json.schema.SchemaProvider;
import com.vorstdev.test.json.schema.SchemaValidator;
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
        ConstraintsValidator constraintsValidator = new ConstraintsValidator(schemaDefinition, objectMapper);

        JsonValidator jsonValidator = new JsonValidatorImpl(schemaValidator, constraintsValidator);

        assertThat(jsonValidator.validate("/com/vorstdev/test/json/user.json")).isTrue();
        assertThat(jsonValidator.validate("/com/vorstdev/test/json/user-error0.json")).isFalse();
        assertThat(jsonValidator.validate("/com/vorstdev/test/json/user-error1.json")).isFalse();
        assertThat(jsonValidator.validate("/com/vorstdev/test/json/user-error2.json")).isFalse();
    }

}
