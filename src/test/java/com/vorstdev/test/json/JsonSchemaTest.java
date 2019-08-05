package com.vorstdev.test.json;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

public class JsonSchemaTest {

    @Test
    public void should_read_schema() {
        SchemaValidator schemaValidator = new SchemaValidator(
                "/com/vorstdev/test/json/schema.json",
                new ObjectMapper());
        assertThat(schemaValidator.validate("/com/vorstdev/test/json/user.json")).isTrue();
        assertThat(schemaValidator.validate("/com/vorstdev/test/json/user-error0.json")).isFalse();
        assertThat(schemaValidator.validate("/com/vorstdev/test/json/user-error1.json")).isFalse();
        assertThat(schemaValidator.validate("/com/vorstdev/test/json/user-error2.json")).isFalse();
    }

}
