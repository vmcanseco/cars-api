package com.javatraining.springboot.carsapi.controller;

import com.javatraining.springboot.carsapi.model.Car;
import com.javatraining.springboot.carsapi.service.ServiceDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class CarController {

    @Autowired
    ServiceDef<Car> carService;

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getAllCars(){
        return new ResponseEntity<>(carService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/cars/{car_vin}")
    public ResponseEntity<Car> getCarByVin(@PathVariable("car_vin") String carVin){
        try{
            return new ResponseEntity<>(carService.findById((carVin)), HttpStatus.OK);
        }catch(NoSuchElementException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/cars")
    public ResponseEntity createCar(@RequestBody Car car){
        try {
            return new ResponseEntity<>(carService.create(car),HttpStatus.CREATED);
        }catch (Exception ex){
            return ResponseEntity.internalServerError().body("Unexpected error when creating car");
        }

    }

    @PutMapping("/cars/{car_vin}")
    public ResponseEntity<Car> updatePerson(@PathVariable("car_vin")String carVin,@RequestBody Car car){
        try{
            return new ResponseEntity<>(carService.update(carVin,car),HttpStatus.OK);

        }catch(NoSuchElementException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/cars/{car_vin}")
    public  ResponseEntity deletePerson(@PathVariable("car_vin")String carVin){

        try {
            carService.delete(carVin);
            return ResponseEntity.noContent().build();
        }catch (NoSuchElementException ex){
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }
}
