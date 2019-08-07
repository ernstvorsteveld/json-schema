package com.vorstdev.test.json.constraints;

public class AttributeConstraint {

    private String attribute;
    private Boolean value = false;

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }
}
