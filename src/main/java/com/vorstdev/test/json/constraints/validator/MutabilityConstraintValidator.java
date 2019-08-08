package com.vorstdev.test.json.constraints.validator;

import com.vorstdev.test.json.constraints.AttributeConstraint;
import com.vorstdev.test.json.constraints.Constraint;
import java.util.List;
import java.util.stream.Collectors;
import org.json.JSONObject;

public class MutabilityConstraintValidator extends AbstractConstraintValidator implements ConstraintValidator {

    @Override
    public boolean isInvalid(Operation operation, JSONObject jsonObject, Constraint constraint) {
        if (operation.equals(Operation.Create)) {
            return false;
        }

        List<AttributeConstraint> collect = constraint.getConstraints().stream()
                .filter(c -> isInvalid(c, jsonObject))
                .collect(Collectors.toList());
        return !collect.isEmpty();
    }

    /**
     * The object may not contain the attributes that are in the MutabilityContraints.
     */
    private boolean isInvalid(
            AttributeConstraint attributeConstraint,
            JSONObject jsonObject) {
        if (!attributeConstraint.getValue()) {
            return false;
        }

        Object value = getValue(attributeConstraint.getAttribute(), jsonObject);
        return value == null;
    }
}
