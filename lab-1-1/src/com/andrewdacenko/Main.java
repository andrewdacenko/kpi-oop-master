package com.andrewdacenko;

import org.json.JSONArray;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try {
            String file = readFile("triangles.json", StandardCharsets.UTF_8);
            JSONArray arr = new JSONArray(file);
            System.out.println(arr);
            ArrayList<Triangle> triangles = new ArrayList<Triangle>();

            for (int i = 0; i < arr.length(); i++) {
                Triangle t = new Triangle(arr.getJSONArray(i));
                triangles.add(t);
            }

            System.out.println(triangles);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
