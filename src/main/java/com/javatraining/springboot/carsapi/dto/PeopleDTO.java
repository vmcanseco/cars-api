package com.javatraining.springboot.carsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeopleDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    //private Set<CarDTO> cars;
}
