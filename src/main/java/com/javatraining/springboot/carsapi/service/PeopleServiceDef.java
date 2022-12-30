package com.javatraining.springboot.carsapi.service;

import com.javatraining.springboot.carsapi.model.Car;
import com.javatraining.springboot.carsapi.model.People;

import java.util.List;

public interface PeopleServiceDef {

     List<People> getAll();

    People findById(String id);

    People create(People person);

    People update(String id, People person);

    void delete(String id);

    People addCar(String personId, Car car);

    People removeCar(String personId, Car car);
}
