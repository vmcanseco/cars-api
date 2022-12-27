package com.javatraining.springboot.carsapi.model;

import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "people")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class People {
    @Id
    @Column(name = "person_id",unique = true,updatable = false)
    @EqualsAndHashCode.Include()
    private String id;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    private String gender;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(name="people_car",
    joinColumns = {@JoinColumn(name = "person_id")},
    inverseJoinColumns = {@JoinColumn(name = "vin")})
    private Set<Car> cars = new HashSet<>();

    public People() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public void addCar(Car car){
        cars.add(car);
        //car.getPeople().add(this);
    }

    public void removeCar(Car car){
        cars.remove(car);
    }
}
