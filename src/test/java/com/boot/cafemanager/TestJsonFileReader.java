package com.boot.cafemanager;

import com.boot.cafemanager.util.JsonFileReader;

import java.io.IOException;

public class TestJsonFileReader {

    public static String readAsText(String fileName) throws IOException {
        return JsonFileReader.readAsText("src/test/resources/json-files/" + fileName);
    }
}
