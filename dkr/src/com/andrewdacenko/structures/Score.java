package com.andrewdacenko.structures;

public class Score {
    public String discipline;
    public Integer mark;

    public Score(Object[] obj) {
        discipline = (String) obj[0];
        mark = Integer.parseInt((String) obj[1]);
    }

    @Override
    public String toString() {
        return discipline + ": " + mark.toString();
    }
}
