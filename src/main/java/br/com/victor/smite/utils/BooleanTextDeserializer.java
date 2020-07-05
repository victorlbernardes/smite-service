package br.com.victor.smite.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class BooleanTextDeserializer extends JsonDeserializer<Boolean> {

    @Override
    public Boolean getNullValue(DeserializationContext ctxt)  {
        return false;
    }

    @Override
    public Boolean deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        return jsonParser.getText().equalsIgnoreCase("y") || jsonParser.getText().equalsIgnoreCase("true");
    }
}
