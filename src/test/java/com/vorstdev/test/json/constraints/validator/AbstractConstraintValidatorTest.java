package com.vorstdev.test.json.constraints.validator;


import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;

public class AbstractConstraintValidatorTest {

    @Test
    public void should_get_value_from_map() throws IOException {
        MutabilityConstraintValidator validator = new MutabilityConstraintValidator();

        JSONObject jsonSubject = new JSONObject(
                new JSONTokener(AbstractConstraintValidatorTest.class
                        .getResourceAsStream("/com/vorstdev/test/json/user.json")));

        assertThat(validator.getValue("age", jsonSubject)).isEqualTo(resultList(65));
        assertThat(validator.getValue("length", jsonSubject)).isEqualTo(resultList(176));
        assertThat(validator.getValue("machines.name", jsonSubject)).isEqualTo(resultList("Drill"));
    }

    private Object resultList(Object... elements) {
        List<Object> ages = new ArrayList<>();
        for (int i = 0; i < elements.length; i++) {
            ages.add(elements[i]);
        }
        return ages;
    }

}