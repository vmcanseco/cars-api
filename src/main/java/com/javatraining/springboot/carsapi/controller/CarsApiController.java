package com.javatraining.springboot.carsapi.controller;

import com.javatraining.springboot.carsapi.dto.CarDTO;
import com.javatraining.springboot.carsapi.dto.PeopleDTO;
import com.javatraining.springboot.carsapi.model.Car;
import com.javatraining.springboot.carsapi.service.CarServiceDef;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import javax.annotation.Generated;
import java.util.List;
import java.util.stream.Collectors;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-01-27T15:27:05.800-06:00[America/Chicago]")
@Controller
@RequestMapping("${openapi.peopleCarsOpenAPI30.base-path:/api}")
public class CarsApiController implements CarsApi {

    @Autowired
    private CarServiceDef carService;
    @Autowired
    private ModelMapper modelMapper;

    private final NativeWebRequest request;

    @Autowired
    public CarsApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public ResponseEntity<CarDTO> createCar(CarDTO car) {
        return new ResponseEntity<>(modelMapper.map(carService.create(modelMapper.map(car, Car.class)), CarDTO.class), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteCar(String carVin) {
        carService.delete(carVin);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<PeopleDTO>> getAllCarPeople(String carVin) {
        Car car = new Car();
        car.setVin(carVin);
        return new ResponseEntity<>(carService.getAllPeople(car)
                .stream()
                .map(person -> modelMapper.map(person, PeopleDTO.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CarDTO>> getAllCars() {
        List<CarDTO> cars = carService.getAll().stream().map(car -> modelMapper.map(car, CarDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CarDTO> getCarByVin(String carVin) {
        return new ResponseEntity<>(modelMapper.map(carService.findById((carVin)), CarDTO.class), HttpStatus.OK);

    }
    @Override
    public ResponseEntity<CarDTO> updateCar(String carVin, CarDTO car) {
        return new ResponseEntity<>(modelMapper.map(carService.update(carVin, modelMapper.map(car, Car.class)), CarDTO.class), HttpStatus.OK);
    }
}
