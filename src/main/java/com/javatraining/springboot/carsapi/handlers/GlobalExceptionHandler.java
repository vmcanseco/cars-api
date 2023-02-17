package com.javatraining.springboot.carsapi.handlers;


import com.javatraining.springboot.carsapi.exceptions.GenericException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = GenericException.class)
    public ResponseEntity handleGenericException(GenericException exception){
        return ResponseEntity.status(exception.getCode()).body(exception.getMessage());
    }
}
