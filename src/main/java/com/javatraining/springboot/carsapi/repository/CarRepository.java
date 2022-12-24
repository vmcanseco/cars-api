package com.javatraining.springboot.carsapi.repository;

import com.javatraining.springboot.carsapi.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,String> {
}
