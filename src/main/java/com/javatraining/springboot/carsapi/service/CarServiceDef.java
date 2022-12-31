package com.javatraining.springboot.carsapi.service;

import com.javatraining.springboot.carsapi.model.Car;
import com.javatraining.springboot.carsapi.model.People;

import java.util.List;

public interface CarServiceDef {

    List<Car> getAll();

    Car findById(String id);

    Car create(Car car);

    Car update(String id, Car car);

    void delete(String id);

    List<People> getAllPeople(Car  car);
}
