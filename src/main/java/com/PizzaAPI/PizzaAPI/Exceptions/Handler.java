package com.PizzaAPI.PizzaAPI.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Contoller Advice:
 *
 */

@ControllerAdvice
public class Handler {

    // Handle Exception200
    @ExceptionHandler(value = {Exception200.class})
    public ResponseEntity<Object> handle200(Exception200 exception){

        ExceptionDetails ex = new ExceptionDetails(exception.getMessage());
        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    }

    // Handle Exception400
    @ExceptionHandler(value = {Exception400.class})
    public ResponseEntity<Object> handle400(Exception400 exception){

        ExceptionDetails ex = new ExceptionDetails(exception.getMessage());
        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    }

    // Handle Exception404
    @ExceptionHandler(value = {Exception404.class})
    public ResponseEntity<Object> handle404(Exception404 exception){

        ExceptionDetails ex = new ExceptionDetails(exception.getMessage());
        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    }

    // Handle Exception412
    @ExceptionHandler(value = {Exception412.class})
    public ResponseEntity<Object> handle412(Exception412 exception){

        ExceptionDetails ex = new ExceptionDetails(exception.getMessage());
        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    }

    // Handle Exception422
    @ExceptionHandler(value = {Exception422.class})
    public ResponseEntity<Object> handle422(Exception422 exception){

        ExceptionDetails ex = new ExceptionDetails(exception.getMessage());
        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    }
}