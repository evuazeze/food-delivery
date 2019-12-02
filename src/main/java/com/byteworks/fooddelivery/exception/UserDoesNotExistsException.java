package com.byteworks.fooddelivery.exception;

public class UserDoesNotExistsException extends RuntimeException {
    public UserDoesNotExistsException(String message) { super(message); }
}
