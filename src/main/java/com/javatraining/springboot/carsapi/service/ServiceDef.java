package com.javatraining.springboot.carsapi.service;

import java.util.List;

public interface ServiceDef<T> {

     List<T> getAll();

     T findById(String id);

    T create(T entity);

    T update(String id, T entity);

    void delete(String id);
}
