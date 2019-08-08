package com.vorstdev.test.json.constraints.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class AbstractConstraintValidator {

    public List<Object> getValue(String attribute, JSONObject source) {
        if (attribute == null || attribute.isEmpty()) {
            throw new AttributeGetException("Attribute is null.");
        }
        List<String> parts = Arrays.stream(attribute.split("\\.")).collect(Collectors.toList());

        if (parts.isEmpty()) {
            throw new AttributeGetException("No parts found in attribute value: " + attribute);
        }

        return getValue(parts.get(0), parts.size() == 1 ? null : parts.subList(1, parts.size()), source);
    }

    private List<Object> getValue(String attribute, List<String> parts, JSONObject source) {
        Object object = source.get(attribute);
        if (parts == null) {
            ArrayList<Object> objects = new ArrayList<>();
            if (object != null) {
                objects.add(object);
            }
            return objects;
        }

        if (object instanceof JSONArray) {
            return getValue(parts.get(0), parts.size() == 1 ? null : parts.subList(1, parts.size()),
                    (JSONArray) object);
        } else {
            return getValue(parts.get(0), parts.size() == 1 ? null : parts.subList(1, parts.size()),
                    (JSONObject) object);
        }
    }

    private List<Object> getValue(String attribute, List<String> parts, JSONArray source) {
        List<Object> result = new ArrayList<>();

        for (int i = 0; i < source.length(); i++) {
            List<Object> subList = getValue(attribute, parts, (JSONObject) source.get(i));
            result.addAll(subList);
        }
        return result;
    }

}
