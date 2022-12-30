package com.javatraining.springboot.carsapi.repository;

import com.javatraining.springboot.carsapi.model.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<People,String> {


}
