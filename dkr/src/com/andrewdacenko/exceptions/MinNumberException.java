package com.andrewdacenko.exceptions;

public class MinNumberException extends Exception {
    private int value;

    public MinNumberException(String message, int value) {
        super(message);
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
