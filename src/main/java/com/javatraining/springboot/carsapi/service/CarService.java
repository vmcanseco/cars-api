package com.javatraining.springboot.carsapi.service;

import com.javatraining.springboot.carsapi.exceptions.GenericException;
import com.javatraining.springboot.carsapi.model.Car;
import com.javatraining.springboot.carsapi.model.People;
import com.javatraining.springboot.carsapi.repository.CarRepository;
import com.javatraining.springboot.carsapi.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CarService implements CarServiceDef{
    private final CarRepository carRepository;
    private final PeopleRepository peopleRepository;

    public CarService(CarRepository carRepository, PeopleRepository peopleRepository) {
        this.carRepository = carRepository;
        this.peopleRepository = peopleRepository;
    }

    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @Override
    public Car findById(String vin) {
        Optional<Car> car = carRepository.findById(vin);
        try {
            return car.get();
        }catch (NoSuchElementException ex){
            throw new GenericException(404,"Not car found with VIN: "+vin);
        }
    }

    @Override
    public Car create(Car car) {
        try{
            return carRepository.save(car);
        }catch (Exception ex){
            throw new GenericException(500,"Unexpected error when creating car");
        }
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
            throw new GenericException(404,"Not car found with VIN: "+car.getVin());
        }
    }

    @Override
    public void delete(String vin) {
        if (carRepository.existsById(vin)){
            carRepository.deleteById(vin);
        }else{
            throw new GenericException(404,"Not car found with VIN: "+vin);
        }

    }

    @Override
    public List<People> getAllPeople(Car car) {
        if (carRepository.existsById(car.getVin())) {
            return peopleRepository.findAllByCars(car);
        }else{
            throw new GenericException(404,"Not car found with VIN: "+car.getVin());
        }
    }
}
