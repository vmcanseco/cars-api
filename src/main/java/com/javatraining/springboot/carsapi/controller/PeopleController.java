package com.javatraining.springboot.carsapi.controller;

import com.javatraining.springboot.carsapi.model.Car;
import com.javatraining.springboot.carsapi.model.People;
import com.javatraining.springboot.carsapi.service.PeopleService;
import com.javatraining.springboot.carsapi.service.ServiceDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class PeopleController {

    @Autowired
    PeopleService peopleService;

    @GetMapping("/people")
    public ResponseEntity<List<People>> getAllPeople(){
        return new ResponseEntity<>(peopleService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/people/{person_id}")
    public ResponseEntity<People> getPersonById(@PathVariable("person_id") String personId){
        try{
           return new ResponseEntity<>(peopleService.findById((personId)), HttpStatus.OK);
        }catch(NoSuchElementException ex){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/people")
    public ResponseEntity createPerson(@RequestBody People person){
        try {
           return new ResponseEntity<>(peopleService.create(person),HttpStatus.CREATED);
        }catch (Exception ex){
            return ResponseEntity.internalServerError().body("Unexpected error when creating person");
        }

    }

    @PutMapping("/people/{person_id}")
    public ResponseEntity<People> updatePerson(@PathVariable("person_id")String personId,@RequestBody People person){
       try{
           return new ResponseEntity<>(peopleService.update(personId,person),HttpStatus.OK);

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
        public ResponseEntity<People> addCar(@PathVariable("person_id") String personId,@RequestBody Car car){
            try{
                return new ResponseEntity<>(peopleService.addCar(personId,car),HttpStatus.OK);
            }catch (NoSuchElementException ex){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @DeleteMapping("/people/{person_id}/car")
    public ResponseEntity<People> removeCar(@PathVariable("person_id") String personId,@RequestBody Car car){
        try{
            return new ResponseEntity<>(peopleService.removeCar(personId,car),HttpStatus.NO_CONTENT);
        }catch (NoSuchElementException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }
}
