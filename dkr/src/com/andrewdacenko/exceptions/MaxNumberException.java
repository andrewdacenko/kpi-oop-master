package com.andrewdacenko.exceptions;

public class MaxNumberException extends Exception {
    private int value;

    public MaxNumberException(String message, int value) {
        super(message);
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
