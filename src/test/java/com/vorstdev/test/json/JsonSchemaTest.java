package com.vorstdev.test.json;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;

public class JsonSchemaTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void should_read_schema() {
        assertThat(validate("/com/vorstdev/test/json/schema.json", "/com/vorstdev/test/json/user.json")).isTrue();
        assertThat(validate("/com/vorstdev/test/json/schema.json", "/com/vorstdev/test/json/user-error0.json"))
                .isFalse();
        assertThat(validate("/com/vorstdev/test/json/schema.json", "/com/vorstdev/test/json/user-error1.json"))
                .isFalse();
        assertThat(validate("/com/vorstdev/test/json/schema.json", "/com/vorstdev/test/json/user-error2.json"))
                .isFalse();
    }

    private boolean validate(String schemaFile, String userFile) {
        JSONObject jsonSchema = new JSONObject(
                new JSONTokener(JsonSchemaTest.class.getResourceAsStream(schemaFile)));
        JSONObject jsonSubject = new JSONObject(
                new JSONTokener(JsonSchemaTest.class.getResourceAsStream(userFile)));

        Schema schema = SchemaLoader.load(jsonSchema);

        try {
            schema.validate(jsonSubject);
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
