package com.javatraining.springboot.carsapi.service;

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
public class PeopleService implements ServiceDef<People> {

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<People> getAll() {
        List<People> people = peopleRepository.findAll();
        return people;
    }

    @Override
    public People findById(String personId) {
        Optional<People> person = peopleRepository.findById(personId);
        return person.get();
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
            throw new NoSuchElementException("Person not found");
        }
    }

    @Override
    public void delete(String personId) {
        if (peopleRepository.existsById(personId)){
            peopleRepository.deleteById(personId);
        }else {
            throw new NoSuchElementException("Not person found with Id: "+personId);
        }
    }

    public People addCar(String personId, Car car) {
        People savedPerson = peopleRepository.findById(personId).map(person -> {
            String vin = car.getVin();
            Optional<Car> foundCar = carRepository.findById(vin);
            if (foundCar.isPresent()) {
                person.addCar(foundCar.get());
                return peopleRepository.save(person);
            } else {
                person.addCar(car);
                return peopleRepository.save(person);
            }

        }).orElseThrow(() -> new NoSuchElementException("Not found person with Id: " + personId));
        return savedPerson;
    }

    public People removeCar(String personId, Car car) {
        People savedPerson;
        savedPerson = peopleRepository.findById(personId).map(person -> {
            String vin = car.getVin();
            Car foundCar = carRepository.findById(vin)
                    .orElseThrow(() -> new NoSuchElementException("Not found car with VIN: " + car.getVin()));
            person.removeCar(foundCar);
            return peopleRepository.save(person);

        }).orElseThrow(() -> new NoSuchElementException("Not found person with Id: " + personId));
        return savedPerson;
    }
}
