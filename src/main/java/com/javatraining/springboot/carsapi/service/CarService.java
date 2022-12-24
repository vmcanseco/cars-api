package com.javatraining.springboot.carsapi.service;

import com.javatraining.springboot.carsapi.model.Car;
import com.javatraining.springboot.carsapi.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CarService implements ServiceDef<Car>{
    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Car> getAll() {
        List<Car> cars = carRepository.findAll();
        return cars;
    }

    @Override
    public Car findById(String vin) {
        Optional<Car> car = carRepository.findById(vin);
        return car.get();
    }

    @Override
    public Car create(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car update(String vin, Car car) {
        Optional<Car> foundCar = carRepository.findById(vin);

        if (foundCar.isPresent()){
            Car updatedCar = foundCar.get();
            updatedCar.setBrand(car.getBrand());
            updatedCar.setColor(car.getColor());
            updatedCar.setModel(car.getModel());
            updatedCar.setYear(car.getYear());
            return carRepository.save(updatedCar);

        }else{
            throw new NoSuchElementException("Not car found with VIN: "+car.getVin());
        }
    }

    @Override
    public void delete(String vin) {
        if (carRepository.existsById(vin)){
            carRepository.deleteById(vin);
        }else{
            throw new NoSuchElementException("Not car found with VIN: "+vin);
        }

    }
}