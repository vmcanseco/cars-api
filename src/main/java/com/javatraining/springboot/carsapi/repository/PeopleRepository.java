package com.javatraining.springboot.carsapi.repository;

import com.javatraining.springboot.carsapi.model.Car;
import com.javatraining.springboot.carsapi.model.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleRepository extends JpaRepository<People,String> {

        List<People> findAllByCars(Car car);

}
