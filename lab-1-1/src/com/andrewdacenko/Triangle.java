package com.andrewdacenko;

import org.json.JSONArray;

import java.util.ArrayList;

public class Triangle {
    ArrayList<TriangleHead> heads;
    String type;

    public static final String[] TRIANGLE_TYPES = new String[] {
            "right-angled",
            "equilateral",
            "isosceles",
            "scalene"
    };

    public Triangle(JSONArray jHeads) {
        heads = getHeads(jHeads);
        type = getType();
    }

    @Override public String toString() {
        return "Triangle: type=" + type + ",heads=" + heads;
    }

    private String getType() {
        return TRIANGLE_TYPES[0];
    }

    public static ArrayList<TriangleHead> getHeads(JSONArray jHeads) {
        ArrayList<TriangleHead> aHeads = new ArrayList<TriangleHead>(3){
            private static final long serialVersionUID = 1L;

            @Override
            public String toString() {
                String result = "[\n";
                for (int i = 0; i < this.size(); i++) {
                    result += " " + this.get(i) + "\n";
                }
                return result + "]";
            }
        };

        for (int i = 0; i < jHeads.length(); i++) {
            TriangleHead tHead = new TriangleHead(jHeads.getJSONArray(i));

            aHeads.add(tHead);
        }

        return aHeads;
    }
}
