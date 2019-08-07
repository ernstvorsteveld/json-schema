package com.vorstdev.test.json.schema;

import com.vorstdev.test.json.JsonSchemaSettings;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

public class SchemaProvider {

    private final Schema schema;

    public SchemaProvider(JsonSchemaSettings jsonSchemaSettings) {
        this.schema = SchemaLoader.load(new JSONObject(new JSONTokener(jsonSchemaSettings.getSchema())));
    }

    public Schema getSchema() {
        return schema;
    }
}
