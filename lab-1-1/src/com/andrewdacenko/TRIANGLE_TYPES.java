package com.andrewdacenko;

public enum TRIANGLE_TYPES {
    RIGHT_ALIGNED("right-angled"),
    EQUILATERAL("equilateral"),
    ISOSCELES("isosceles"),
    SCALENE("scalene");

    private final String text;

    /**
     * @param text
     */
    TRIANGLE_TYPES(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}