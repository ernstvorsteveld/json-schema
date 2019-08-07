package com.vorstdev.test.json.schema;

public class SchemaDefinition {

    private final String schemaFile;
    private final String validators;

    public SchemaDefinition(String schemaFile, String validators) {
        this.schemaFile = schemaFile;
        this.validators = validators;
    }

    public String getSchemaFile() {
        return schemaFile;
    }

    public String getValidators() {
        return validators;
    }
}
