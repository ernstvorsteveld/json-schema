package com.vorstdev.test.json.constraints;

import lombok.Data;

@Data
public class Constraints {

    private AttributeList caseExact;
    private AttributeList mutability;
    private AttributeList uniqueness;
    private AttributeList returned;

}
