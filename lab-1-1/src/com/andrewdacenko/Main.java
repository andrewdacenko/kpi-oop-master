package com.andrewdacenko;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        try {
            String file = readFile("triangles.json", StandardCharsets.UTF_8);
            JSONArray arr = new JSONArray(file);
            System.out.println(arr);
            HashSet<HashSet<TriangleHead>> trianglesHashSet = new HashSet<HashSet<TriangleHead>>();

            for (int i = 0; i < arr.length(); i++) {
                HashSet<TriangleHead> heads = new HashSet<TriangleHead>();

                JSONArray headsArray = arr.getJSONArray(i);

                for (int j = 0; j < headsArray.length(); j++) {
                    JSONArray headPoints = headsArray.getJSONArray(j);
                    TriangleHead triangleHead = new TriangleHead(headPoints);
                    heads.add(triangleHead);
                }

                if (heads.size() == 3) {
                    trianglesHashSet.add(heads);
                }
            }

            HashSet<Triangle> triangles = new HashSet<Triangle>();

            for (HashSet<TriangleHead> heads : trianglesHashSet) {
                Triangle triangle = new Triangle(heads);
                triangles.add(triangle);
            }

            for (Triangle triangle : triangles) {
                System.out.println(triangle);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
