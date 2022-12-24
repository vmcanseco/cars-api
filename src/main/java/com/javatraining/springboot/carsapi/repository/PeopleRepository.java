package com.javatraining.springboot.carsapi.repository;

import com.javatraining.springboot.carsapi.model.People;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository<People,String> {


}
