package com.javatraining.springboot.carsapi.controller;

import com.javatraining.springboot.carsapi.converters.DTOConverter;
import com.javatraining.springboot.carsapi.dto.CarDTO;
import com.javatraining.springboot.carsapi.dto.PeopleDTO;
import com.javatraining.springboot.carsapi.model.People;
import com.javatraining.springboot.carsapi.service.CarServiceDef;
import com.javatraining.springboot.carsapi.service.PeopleServiceDef;
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
public class PeopleApiController implements PeopleApi {
    @Autowired
    private  PeopleServiceDef peopleService;
    @Autowired
    private  CarServiceDef carServiceDef;
    private final NativeWebRequest request;

    @Autowired
    public PeopleApiController(NativeWebRequest request) {
        this.request = request;
    }


    @Override
    public ResponseEntity<Void> addCar(String personId, CarDTO car) {
        peopleService.addCar(personId,DTOConverter.toCar(car));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PeopleDTO> createPerson(PeopleDTO people) {
        return new ResponseEntity<>(DTOConverter.toPeopleDTO(peopleService.create(DTOConverter.toPeople(people))),HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deletePerson(String personId) {
        peopleService.delete(personId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<PeopleDTO>> getAllPeople() {
        List<PeopleDTO> people = peopleService.getAll().stream()
                .map(DTOConverter::toPeopleDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PeopleDTO> getPersonById(String personId) {
            return new ResponseEntity<>(DTOConverter.toPeopleDTO(peopleService.findById(personId)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CarDTO>> getPersonCars(String personId) {
        People person = new People();
        person.setId(personId);
        return new ResponseEntity<>(peopleService.getAllCars(person)
                .stream()
                .map(DTOConverter::toCarDTO)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> removeCar(String personId, CarDTO car) {
        peopleService.removeCar(personId,DTOConverter.toCar(car));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<PeopleDTO> updatePerson(String personId, PeopleDTO people) {
        return new ResponseEntity<>(DTOConverter.toPeopleDTO(peopleService.update(personId,DTOConverter.toPeople(people))),HttpStatus.OK);
    }
}
