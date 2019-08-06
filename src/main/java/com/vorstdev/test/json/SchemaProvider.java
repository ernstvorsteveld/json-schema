package com.vorstdev.test.json;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

public class SchemaProvider {

    private final JSONObject jsonSchema;
    private final Schema schema;

    public SchemaProvider(JsonSchemaSettings jsonSchemaSettings) {
        this.jsonSchema = new JSONObject(new JSONTokener(jsonSchemaSettings.getSchema()));
        this.schema = SchemaLoader.load(jsonSchema);
    }

    public Schema getSchema() {
        return schema;
    }
}
