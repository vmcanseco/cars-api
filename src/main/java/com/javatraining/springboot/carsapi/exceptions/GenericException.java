package com.javatraining.springboot.carsapi.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericException extends RuntimeException{
    private int code;
    private String message;

    public GenericException(int code, String message){
        super(message);
        this.message=message;
        this.code=code;
    }
}
