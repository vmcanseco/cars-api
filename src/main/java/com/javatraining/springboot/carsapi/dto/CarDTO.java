package com.javatraining.springboot.carsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {

    private String vin;
    private String brand;
    private String color;
    private String model;
    private int year;
}
