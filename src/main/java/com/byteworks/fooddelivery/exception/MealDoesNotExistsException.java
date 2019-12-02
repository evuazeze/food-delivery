package com.byteworks.fooddelivery.exception;

public class MealDoesNotExistsException extends RuntimeException {
    public MealDoesNotExistsException(String message) { super(message); }
}
