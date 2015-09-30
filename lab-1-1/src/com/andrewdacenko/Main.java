package com.andrewdacenko;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        try {
            String file = readFile("triangles.json", StandardCharsets.UTF_8);
            System.out.println(file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
