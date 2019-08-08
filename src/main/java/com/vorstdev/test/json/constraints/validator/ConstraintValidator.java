package com.vorstdev.test.json.constraints.validator;

import com.vorstdev.test.json.constraints.Constraint;
import java.util.Map;
import org.json.JSONObject;

public interface ConstraintValidator {

    boolean isInvalid(Operation operation, JSONObject jsonObject, Constraint constraint);

    enum Operation {
        Create, Update, Delete
    }

}
