package com.vorstdev.test.json.constraints;

public class Constraint {

    private String type;
    private AttributeConstraintList constraints;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AttributeConstraintList getConstraints() {
        return constraints;
    }

    public void setConstraints(AttributeConstraintList constraints) {
        this.constraints = constraints;
    }
}
