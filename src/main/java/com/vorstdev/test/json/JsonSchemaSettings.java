package com.vorstdev.test.json;

import java.io.InputStream;

public class JsonSchemaSettings {

    private final String schemaFilename;

    public JsonSchemaSettings(String schemaFilename) {
        this.schemaFilename = schemaFilename;
    }

    public InputStream getSchema() {
        return JsonSchemaSettings.class.getResourceAsStream(this.schemaFilename);
    }

}
