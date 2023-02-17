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
import java.util.UUID;

@Service
public class PeopleService implements PeopleServiceDef {

    private final PeopleRepository peopleRepository;

    private final CarRepository carRepository;

    public PeopleService(@Autowired PeopleRepository peopleRepository, @Autowired CarRepository carRepository) {
        this.peopleRepository = peopleRepository;
        this.carRepository = carRepository;
    }

    @Override
    public List<People> getAll() {
        return peopleRepository.findAll();
    }

    @Override
    public People findById(String personId) {
        Optional<People> person = peopleRepository.findById(personId);
        if (person.isPresent()){
            return person.get();
        }else{
            throw new GenericException(404,"Not person found with Id: "+personId);
        }
    }

    @Override
    public People create(People person) {
        person.setId(UUID.randomUUID().toString());
        return peopleRepository.save(person);
    }

    @Override
    public People update(String personId, People person) {
        Optional<People> foundPerson = peopleRepository.findById(personId);

        if (foundPerson.isPresent()) {
            People updatedPerson = foundPerson.get();
            updatedPerson.setEmail(person.getEmail());
            updatedPerson.setGender(person.getGender());
            updatedPerson.setFirstName(person.getFirstName());
            updatedPerson.setLastName(person.getLastName());
            updatedPerson.setCars(person.getCars());
            return peopleRepository.save(updatedPerson);
        } else {
            throw new GenericException(404,"Not person found with Id: "+personId);
        }
    }

    @Override
    public void delete(String personId) {
        if (peopleRepository.existsById(personId)){
            peopleRepository.deleteById(personId);
        }else {
            throw new GenericException(404,"Not person found with Id: "+personId);
        }
    }

    @Override
    public People addCar(String personId, Car car) {
        return peopleRepository.findById(personId).map(person -> {
            String vin = car.getVin();
            Optional<Car> foundCar = carRepository.findById(vin);
            if (foundCar.isPresent()) {
                person.addCar(foundCar.get());
            } else {
                person.addCar(car);
            }
            return peopleRepository.save(person);

        }).orElseThrow(() ->  new GenericException(404,"Not person found with Id: "+personId));
        //return savedPerson;
    }

    @Override
    public People removeCar(String personId, Car car) {
        People savedPerson;
        savedPerson = peopleRepository.findById(personId).map(person -> {
            String vin = car.getVin();
            Car foundCar = carRepository.findById(vin)
                    .orElseThrow(() -> new NoSuchElementException("Not found car with VIN: " + car.getVin()));
            person.removeCar(foundCar);
            return peopleRepository.save(person);

        }).orElseThrow(() ->  new GenericException(404,"Not person found with Id: "+personId));
        return savedPerson;
    }

    @Override
    public List<Car> getAllCars(People person) {
        if (peopleRepository.existsById(person.getId())) {
            return carRepository.findAllByPeople(person);
        }else {
            throw new GenericException(404,"Not person found with Id: "+person.getId());
        }
    }


}
