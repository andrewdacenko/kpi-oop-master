package com.andrewdacenko;

import org.json.JSONArray;
import org.json.JSONException;

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

    public Triangle(JSONArray jHeads) throws JSONException {
        heads = getHeads(jHeads);
        type = getType();
    }

    @Override public String toString() {
        return "Triangle: type=" + type + ",heads=" + heads;
    }

    private String getType() {
        ArrayList<Double> sides = new ArrayList<Double>();

        for (TriangleHead h : heads) {
            int nextIndex = heads.indexOf(h) >= heads.size() - 1 ? 0 : heads.indexOf(h) + 1;

            TriangleHead nextHead = heads.get(nextIndex);
            Double side = getSide(h, nextHead);
            sides.add(side);
        }

        if (isEqualAllSides(sides)) {
            return TRIANGLE_TYPES[1];
        }

        if (isRectangular(sides)) {
            return TRIANGLE_TYPES[0];
        }

        if (isEqualTwoSides(sides)) {
            return TRIANGLE_TYPES[2];
        }

        return TRIANGLE_TYPES[3];
    }

    private boolean isEqualTwoSides(ArrayList<Double> sides) {
        return sides.get(0).equals(sides.get(1)) ||
                sides.get(0).equals(sides.get(2)) ||
                sides.get(1).equals(sides.get(2));
    }

    private boolean isEqualAllSides(ArrayList<Double> sides) {
        return sides.get(0).equals(sides.get(1)) && sides.get(1).equals(sides.get(2));
    }

    private boolean isRectangular(ArrayList<Double> sides) {
        ArrayList<Boolean> isHypExists = new ArrayList<Boolean>();

        isHypExists.add(isHypotenuse(sides, 0, 1, 2));
        isHypExists.add(isHypotenuse(sides, 1, 0, 2));
        isHypExists.add(isHypotenuse(sides, 2, 0, 1));

        return isHypExists.contains(true);
    }

    private boolean isHypotenuse(ArrayList<Double> sides, int hyp, int cat1, int cat2) {
        double hypPow = Math.pow(sides.get(hyp), 2);
        double cat1Pow = Math.pow(sides.get(cat1), 2);
        double cat2Pow = Math.pow(sides.get(cat2), 2);

        return Math.abs(hypPow - cat1Pow - cat2Pow) < 0.12;
    }

    private Double getSide(TriangleHead start, TriangleHead stop) {
        double xLength = Math.pow(start.x - stop.x, 2);
        double yLength = Math.pow(start.y - stop.y, 2);

        double sqrt = Math.sqrt(xLength + yLength);
        return Double.parseDouble(String.format("%.2f", sqrt));
    }

    public static ArrayList<TriangleHead> getHeads(JSONArray jHeads) throws JSONException {
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
