package com.vorstdev.test.json;

import com.vorstdev.test.json.schema.SchemaDefinition;
import java.io.InputStream;

public class JsonSchemaSettings {

    private final SchemaDefinition schemaDefinition;

    public JsonSchemaSettings(SchemaDefinition schemaDefinition) {
        this.schemaDefinition = schemaDefinition;
    }

    public InputStream getSchema() {
        return JsonSchemaSettings.class.getResourceAsStream(this.schemaDefinition.getSchemaFile());
    }

    public InputStream getValidator() {
        return JsonSchemaSettings.class.getResourceAsStream(this.schemaDefinition.getValidators());
    }

}
