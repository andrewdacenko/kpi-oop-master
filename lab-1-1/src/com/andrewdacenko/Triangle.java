package com.andrewdacenko;

import org.json.JSONException;

import java.util.HashSet;
import java.util.Iterator;

public class Triangle {
    private HashSet<TriangleHead> heads;
    private HashSet<Double> sides;
    TRIANGLE_TYPES type;

    public Triangle(HashSet<TriangleHead> hHeads) throws JSONException {
        heads = hHeads;
        type = getType();
    }

    @Override
    public String toString() {
        return "Triangle: type=" + type + ",sides=" + sides + " heads=" + heads;
    }

    private TRIANGLE_TYPES getType() {
        sides = new HashSet<Double>();

        Iterator<TriangleHead> sidesIterator = heads.iterator();
        TriangleHead first = sidesIterator.next();
        TriangleHead second = sidesIterator.next();
        TriangleHead third = sidesIterator.next();

        sides.add(getSide(first, second));
        sides.add(getSide(first, third));
        sides.add(getSide(second, third));

        switch (sides.size()) {
            case 1:
                return TRIANGLE_TYPES.EQUILATERAL;
            case 2:
                return TRIANGLE_TYPES.ISOSCELES;
            case 3:
                if (isRectangular(sides)) {
                    return TRIANGLE_TYPES.RIGHT_ALIGNED;
                }
            default:
                return TRIANGLE_TYPES.SCALENE;
        }
    }

    private boolean isRectangular(HashSet<Double> sides) {
        HashSet<Boolean> isRightSided = new HashSet<Boolean>();

        Iterator<Double> iterator = sides.iterator();
        Double first = iterator.next();
        Double second = iterator.next();
        Double third = iterator.next();

        isRightSided.add(isHypotenuse(first, second, third));
        isRightSided.add(isHypotenuse(second, first, third));
        isRightSided.add(isHypotenuse(third, first, second));

        return isRightSided.contains(true);
    }

    private boolean isHypotenuse(double hyp, double cat1, double cat2) {
        double hypPow = Math.pow(hyp, 2);
        double cat1Pow = Math.pow(cat1, 2);
        double cat2Pow = Math.pow(cat2, 2);

        return Math.abs(hypPow - cat1Pow - cat2Pow) < 0.12;
    }

    private Double getSide(TriangleHead start, TriangleHead stop) {
        double xLength = Math.pow(start.x - stop.x, 2);
        double yLength = Math.pow(start.y - stop.y, 2);

        double sqrt = Math.sqrt(xLength + yLength);
        return Double.parseDouble(String.format("%.2f", sqrt));
    }
}
