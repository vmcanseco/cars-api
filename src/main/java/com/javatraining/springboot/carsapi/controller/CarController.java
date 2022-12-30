package com.javatraining.springboot.carsapi.controller;

import com.javatraining.springboot.carsapi.dto.CarDTO;
import com.javatraining.springboot.carsapi.model.Car;
import com.javatraining.springboot.carsapi.service.CarServiceDef;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CarController {

    private final CarServiceDef carService;

    private final ModelMapper modelMapper;

    public CarController(CarServiceDef carService, ModelMapper modelMapper) {
        this.carService = carService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/cars")
    public ResponseEntity<List<CarDTO>> getAllCars() {
        List<CarDTO> cars = carService.getAll().stream().map(car -> modelMapper.map(car, CarDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/cars/{car_vin}")
    public ResponseEntity<CarDTO> getCarByVin(@PathVariable("car_vin") String carVin) {
        try {
            return new ResponseEntity<>(modelMapper.map(carService.findById((carVin)), CarDTO.class), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/cars")
    public ResponseEntity createCar(@RequestBody CarDTO car) {
        try {
            return new ResponseEntity<>(modelMapper.map(carService.create(modelMapper.map(car, Car.class)), CarDTO.class), HttpStatus.CREATED);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Unexpected error when creating car");
        }

    }

    @PutMapping("/cars/{car_vin}")
    public ResponseEntity<CarDTO> updateCar(@PathVariable("car_vin") String carVin, @RequestBody CarDTO car) {
        try {
            return new ResponseEntity<>(modelMapper.map(carService.update(carVin, modelMapper.map(car, Car.class)), CarDTO.class), HttpStatus.OK);

        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/cars/{car_vin}")
    public ResponseEntity deleteCar(@PathVariable("car_vin") String carVin) {

        try {
            carService.delete(carVin);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }
}
