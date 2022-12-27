package com.javatraining.springboot.carsapi.controller;

import com.javatraining.springboot.carsapi.DTOConverter;
import com.javatraining.springboot.carsapi.dto.CarDTO;
import com.javatraining.springboot.carsapi.dto.PeopleDTO;
import com.javatraining.springboot.carsapi.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PeopleController {

    private final
    PeopleService peopleService;


    public PeopleController(@Autowired PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping("/people")
    public ResponseEntity<List<PeopleDTO>> getAllPeople(){
        List<PeopleDTO> people = peopleService.getAll().stream()
                .map(DTOConverter::toPeopleDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @GetMapping("/people/{person_id}")
    public ResponseEntity<PeopleDTO> getPersonById(@PathVariable("person_id") String personId){
        try{
           return new ResponseEntity<>(DTOConverter.toPeopleDTO(peopleService.findById(personId)), HttpStatus.OK);
        }catch(NoSuchElementException ex){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/people")
    public ResponseEntity createPerson(@RequestBody PeopleDTO person){
        try {
           return new ResponseEntity<>(DTOConverter.toPeopleDTO(peopleService.create(DTOConverter.toPeople(person))),HttpStatus.CREATED);
        }catch (Exception ex){
            return ResponseEntity.internalServerError().body("Unexpected error when creating person");
        }

    }

    @PutMapping("/people/{person_id}")
    public ResponseEntity<PeopleDTO> updatePerson(@PathVariable("person_id")String personId,@RequestBody PeopleDTO person){
       try{
           return new ResponseEntity<>(DTOConverter.toPeopleDTO(peopleService.update(personId,DTOConverter.toPeople(person))),HttpStatus.OK);

        }catch(NoSuchElementException ex){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }

    @DeleteMapping("/people/{person_id}")
    public  ResponseEntity deletePerson(@PathVariable("person_id")String personId){

      try{
          peopleService.delete(personId);
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }catch (NoSuchElementException ex){
          return ResponseEntity.internalServerError().body(ex.getMessage());
      }
    }
    @PostMapping("/people/{person_id}/car")
        public ResponseEntity<PeopleDTO> addCar(@PathVariable("person_id") String personId,@RequestBody CarDTO car){
            try{
                return new ResponseEntity<>(DTOConverter.toPeopleDTO(peopleService.addCar(personId,DTOConverter.toCar(car))),HttpStatus.OK);
            }catch (NoSuchElementException ex){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @DeleteMapping("/people/{person_id}/car")
    public ResponseEntity<PeopleDTO> removeCar(@PathVariable("person_id") String personId,@RequestBody CarDTO car){
        try{
            return new ResponseEntity<>(DTOConverter.toPeopleDTO(peopleService.removeCar(personId,DTOConverter.toCar(car))),HttpStatus.NO_CONTENT);
        }catch (NoSuchElementException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }
}
