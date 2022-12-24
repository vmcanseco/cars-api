package com.javatraining.springboot.carsapi;

import com.javatraining.springboot.carsapi.model.Car;
import com.javatraining.springboot.carsapi.model.People;
import com.javatraining.springboot.carsapi.repository.PeopleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CarsApiApplicationTests {

	@Autowired
	PeopleRepository peopleRepository;

	@Test
	void when_save_person_with_car_then_id_generated_correctly() {
		People person = new People();
		person.setEmail("canseco.victor@gmail.com");
		person.setGender("male");
		person.setFirstName("victor");
		person.setLastName("canseco");

		Car car = new Car();
		car.setYear(1983);
		car.setColor("blue");
		car.setBrand("ford");
		car.setVin("123456789");

		person.getCars().add(car);
		People personSaved = peopleRepository.save(person);

		Assertions.assertNotNull(personSaved);


	}
}