package com.vorstdev.test.json.constraints.validator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractConstraintValidator {

    public Object getValue(String attribute, Map<String, Object> toValidate) {
        if (attribute == null || attribute.isEmpty()) {
            throw new AttributeGetException("Attribute is null.");
        }
        List<String> parts = Arrays.stream(attribute.split("\\.")).collect(Collectors.toList());

        if (parts.isEmpty()) {
            throw new AttributeGetException("No parts found in attribute value: " + attribute);
        }

        Object part = toValidate.get(parts.get(0));
        return "";
    }

}
