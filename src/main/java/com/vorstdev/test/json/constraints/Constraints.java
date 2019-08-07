package com.vorstdev.test.json.constraints;

public class Constraints {

    private AttributeConstraintList caseExact;
    private AttributeConstraintList mutability;
    private AttributeConstraintList uniqueness;
    private AttributeConstraintList returned;

    public AttributeConstraintList getCaseExact() {
        return caseExact;
    }

    public void setCaseExact(AttributeConstraintList caseExact) {
        this.caseExact = caseExact;
    }

    public AttributeConstraintList getMutability() {
        return mutability;
    }

    public void setMutability(AttributeConstraintList mutability) {
        this.mutability = mutability;
    }

    public AttributeConstraintList getUniqueness() {
        return uniqueness;
    }

    public void setUniqueness(AttributeConstraintList uniqueness) {
        this.uniqueness = uniqueness;
    }

    public AttributeConstraintList getReturned() {
        return returned;
    }

    public void setReturned(AttributeConstraintList returned) {
        this.returned = returned;
    }
}
