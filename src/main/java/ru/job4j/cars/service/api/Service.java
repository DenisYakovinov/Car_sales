package ru.job4j.cars.service.api;

import java.util.List;

public interface Service<T> {

    T add(T entity);

    boolean replace(int id, T entity);

    boolean delete(int id);

    List<T> findAll();

    T findById(int id);
}
