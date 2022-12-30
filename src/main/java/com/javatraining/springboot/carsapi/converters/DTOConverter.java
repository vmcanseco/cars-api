package com.javatraining.springboot.carsapi.converters;

import com.javatraining.springboot.carsapi.dto.CarDTO;
import com.javatraining.springboot.carsapi.dto.PeopleDTO;
import com.javatraining.springboot.carsapi.model.Car;
import com.javatraining.springboot.carsapi.model.People;
import org.modelmapper.ModelMapper;

public class DTOConverter {

    private static  final ModelMapper mapper=new ModelMapper();

    public static People toPeople(PeopleDTO people){
        return mapper.map(people,People.class);
    }

    public static PeopleDTO toPeopleDTO(People people){
        return mapper.map(people,PeopleDTO.class);
    }

    public static Car toCar(CarDTO car){
        return mapper.map(car,Car.class);
    }

    public static CarDTO toCarDTO(Car car){
        return mapper.map(car,CarDTO.class);
    }
}
