package com.boot.cafemanager.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public final class JsonFileReader {

    private JsonFileReader() {
        throw new IllegalStateException("JsonFileReader");
    }

    public static String readAsText(String path) throws IOException {

        File file = new File(path);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(file, JsonNode.class).toPrettyString();
    }
}
