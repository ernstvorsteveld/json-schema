package com.vorstdev.test.json.constraints.validator;

import com.vorstdev.test.json.constraints.Constraint;
import java.util.Map;
import org.json.JSONObject;

public class ReturnedConstraintValidator implements ConstraintValidator {

    @Override
    public boolean isInvalid(Operation operation, JSONObject jsonObject, Constraint constraint) {
        return false;
    }
}
