package com.sample.utils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

public class JsonToString {

    public static void main(String... args) {
        String file = "src/test/resources/com/sample/dtables/ShareholderDiscountTest01.json";
        System.out.println(JsonToString.run(file));
    }

    public static String run(String jsonFile) {
        Path path = Paths.get(jsonFile);
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(path);
        } catch(IOException e) {
            System.err.println(e);
        }
        return listToString(lines);
    }

    private static String listToString(List<String> lines) {
        StringBuilder sb = new StringBuilder();
        lines.forEach(a -> {
            sb.append(a);
        });
        return sb.toString();
    }

}
