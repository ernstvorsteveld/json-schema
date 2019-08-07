package com.vorstdev.test.json.constraints.validator;

import com.vorstdev.test.json.constraints.AttributeConstraint;
import com.vorstdev.test.json.constraints.Constraint;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MutabilityConstraintValidator extends AbstractConstraintValidator implements ConstraintValidator {

    @Override
    public boolean isInvalid(Operation operation, Map<String, Object> toValidate, Constraint constraint) {
        if (operation.equals(Operation.Create)) {
            return false;
        }

        List<AttributeConstraint> collect = constraint.getConstraints().stream()
                .filter(c -> isInvalid(c, operation, toValidate))
                .collect(Collectors.toList());
        return !collect.isEmpty();
    }

    /**
     * The object may not contain the attributes that are in the MutabilityContraints.
     */
    private boolean isInvalid(
            AttributeConstraint attributeConstraint,
            Operation operation,
            Map<String, Object> toValidate) {
        if (!attributeConstraint.getValue()) {
            return false;
        }

        Object value = getValue(attributeConstraint.getAttribute(), toValidate);
        return value == null;
    }
}
