package com.PizzaAPI.PizzaAPI.Exceptions;


public class ExceptionDetails {

    private final String message;

    public ExceptionDetails(String message) {
        this.message = message;
    }

    public String getMessage() {
            return message;
        }
}
