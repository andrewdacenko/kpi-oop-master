package com.andrewdacenko.structures;

import java.util.ArrayList;

public class Scores extends ArrayList<Score> {

    public Double getAverage() {
        Double avg = 0.0d;

        for (Score score : this) {
            avg += score.mark;
        }

        return avg / this.size();
    }

    @Override
    public String toString() {
        return String.format("%.2f", getAverage())
                + " (" + this.size() + " "
                + (this.size() > 1 ? "disciplines" : "discipline")
                + ")";
    }
}
