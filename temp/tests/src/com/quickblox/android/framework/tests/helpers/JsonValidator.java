package com.quickblox.android.framework.tests.helpers;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quickblox.android.framework.base.net.results.Result;
import org.eel.kitchen.jsonschema.main.JsonSchema;
import org.eel.kitchen.jsonschema.main.JsonSchemaFactory;
import org.eel.kitchen.jsonschema.report.ValidationReport;
import org.eel.kitchen.jsonschema.util.JsonLoader;

import java.io.IOException;

/**
 * User: Oleg Soroka
 * Date: 19.09.12
 * Time: 11:27
 */
public class JsonValidator {

    public static ValidationReport validate(String schema, Result result) {
        String testData = result.getRawBody();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonFactory jsonFactory = objectMapper.getJsonFactory();

        String schemasRoot = "/com/quickblox/android/framework/tests/validation/schemas/";

        JsonParser jsonParser = null;
        JsonNode testJsonNode = null;
        JsonNode schemaNode = null;

        try {
            jsonParser = jsonFactory.createJsonParser(testData);
            testJsonNode = objectMapper.readTree(jsonParser);
            schemaNode = JsonLoader.fromResource(schemasRoot + schema +
                    ".json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String namespace = "resource:" + schemasRoot;
        JsonSchemaFactory factory = new JsonSchemaFactory.Builder().setNamespace(namespace).build();
        JsonSchema jsonSchema = factory.fromSchema(schemaNode);
        ValidationReport report = jsonSchema.validate(testJsonNode);

        return report;
    }
}